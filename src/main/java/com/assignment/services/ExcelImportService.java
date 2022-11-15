package com.assignment.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.entities.Category;
import com.assignment.entities.Product;
import com.assignment.repositories.AccountRepository;
import com.assignment.repositories.CategoryRepository;
import com.assignment.repositories.ProductRepository;

@Service
public class ExcelImportService {

    @Autowired
    AccountRepository accountRepo;
    
    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    ProductRepository productRepo;

    public static final int COLUMN_INDEX_CATEGORY_ID = 0;
    public static final int COLUMN_INDEX_CATEGORY_NAME = 1;
    public static final int COLUMN_INDEX_CATEGORY_QUANTITY_PRODUCT = 2;
    public static final int COLUMN_INDEX_CATEGORY_AVAILABLE = 3;

    public static final int COLUMN_INDEX_PRODUCT_ID = 0;
    public static final int COLUMN_INDEX_PRODUCT_CATEGORY = 1;
    public static final int COLUMN_INDEX_PRODUCT_NAME = 2;
    public static final int COLUMN_INDEX_PRODUCT_IMAGE = 3;
    public static final int COLUMN_INDEX_PRODUCT_PRICE = 4;
    public static final int COLUMN_INDEX_PRODUCT_QUANTITY = 5;
    public static final int COLUMN_INDEX_PRODUCT_CREATED_DATE = 6;
    public static final int COLUMN_INDEX_PRODUCT_AVAILABLE = 7;

//    private File converMultiPartToFile(MultipartFile file) throws IOException {
//        File convFile = new File(file.getOriginalFilename());
//        FileOutputStream fos = new FileOutputStream(convFile);
//        fos.write(file.getBytes());
//        fos.close();
//        return convFile;
//    }

    public void readExcel(File excelFile) throws IOException {
        InputStream inputStream = new FileInputStream(excelFile);

        Workbook workbook = getWorkbook(inputStream, excelFile.getPath());
        Sheet sheetC = workbook.getSheet("Categories");
        Sheet sheetP = workbook.getSheet("Products");

        readCategorySheet(sheetC);
        readProductSheet(sheetP);
        
        workbook.close();
        inputStream.close();
    }

    private void readCategorySheet(Sheet sheetC) {
        List<Category> listCategories = new ArrayList<>();
        // Lấy tất cả các dòng
        // Iterators là classes mà implement lại phương thức next
        // Nó liên tục trả về các phần tử cho đến khi kết thúc vòng lặp.
        Iterator<Row> iteratorC = sheetC.iterator();
        while (iteratorC.hasNext()) {
            Row nextRowC = iteratorC.next();
            if (nextRowC.getRowNum() == 0) {
                continue;
            }

            // Lấy tất cả các ô
            Iterator<Cell> cellIteratorC = nextRowC.cellIterator();

            Category c = new Category();
            while (cellIteratorC.hasNext()) {
                Cell cellC = cellIteratorC.next();
                Object cellValueC = getCellValue(cellC);
                if (cellValueC == null || cellValueC.toString().isEmpty()) {
                    continue;
                }
                int columnIndex = cellC.getColumnIndex();
                switch (columnIndex) {
                    case COLUMN_INDEX_CATEGORY_ID:
                        c.setId(new BigDecimal((double) cellValueC).intValue());
                        break;
                    case COLUMN_INDEX_CATEGORY_NAME:
                        c.setName((String) getCellValue(cellC));
                        break;
                    case COLUMN_INDEX_CATEGORY_QUANTITY_PRODUCT:
                        c.setQuantityProduct(new BigDecimal((double) cellValueC).intValue());
                        break;
                    case COLUMN_INDEX_CATEGORY_AVAILABLE:
                        c.setAvailable(getCellValue(cellC).equals("Đang bán") ? 0 : 1);
                        break;
                    default:
                        break;
                }

            }
            listCategories.add(c);
        }
        categoryRepo.saveAllAndFlush(listCategories);
    }

    private void readProductSheet(Sheet sheetP) {
        List<Product> listProducts = new ArrayList<>();
        Iterator<Row> iteratorP = sheetP.iterator();
        while (iteratorP.hasNext()) {
            Row nextRowP = iteratorP.next();
            if (nextRowP.getRowNum() == 0) {
                continue;
            }

            Iterator<Cell> cellIteratorP = nextRowP.cellIterator();

            Product p = new Product();
            while (cellIteratorP.hasNext()) {
                Cell cellP = cellIteratorP.next();
                Object cellValueP = getCellValue(cellP);
                if (cellValueP == null || cellValueP.toString().isEmpty()) {
                    continue;
                }
                int columnIndex = cellP.getColumnIndex();
                switch (columnIndex) {
                    case COLUMN_INDEX_PRODUCT_ID:
                        p.setId(new BigDecimal((double) cellValueP).intValue());
                        break;
                    case COLUMN_INDEX_PRODUCT_CATEGORY:
                        String nameOfCategory = getCellValue(cellP).toString().trim();
                        Category cate = categoryRepo.findByName(nameOfCategory);
                        if (cate != null) {
                            p.setCategoryId(cate);
                        } else {
                            categoryRepo.save(new Category(0, nameOfCategory, 0, 0));
                        }
                        break;
                    case COLUMN_INDEX_PRODUCT_NAME:
                        p.setName((String) getCellValue(cellP));
                        break;
                    case COLUMN_INDEX_PRODUCT_IMAGE:
                        p.setImage((String) getCellValue(cellP));
                        break;
                    case COLUMN_INDEX_PRODUCT_PRICE:
                        p.setPrice(new BigDecimal((double) cellValueP).intValue());
                        break;
                    case COLUMN_INDEX_PRODUCT_QUANTITY:
                        p.setQuantity(new BigDecimal((double) cellValueP).intValue());
                        break;
                    case COLUMN_INDEX_PRODUCT_CREATED_DATE:
                        p.setCreatedDate(new Date());
                        break;
                    case COLUMN_INDEX_PRODUCT_AVAILABLE:
                        p.setAvailable(getCellValue(cellP).equals("Đang bán") ? 0 : 1);
                        break;
                    default:
                        break;
                }

            }
            listProducts.add(p);
        }
        for (Product prod : listProducts) {
            prod.setLastModifiedDate(new Date());
            prod.setCreatedUserId(accountRepo.findByAccId(1));
            prod.setLastModifiedUserId(accountRepo.findByAccId(1));
        }
        productRepo.saveAllAndFlush(listProducts);
    }

    // Lấy Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            System.out.println("Đây không phải file excel!");
        }
        return workbook;
    }

    // Lấy giá trị ô
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
                //Kiểu công thức
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }
        return cellValue;
    }

}
