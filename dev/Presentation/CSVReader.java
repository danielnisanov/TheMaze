package Presentation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CSVReader {

    public void loadCategories(String filePath, CategoryPresentation categoryPresentation) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 3) {
                    String category = values[0];
                    String subCategory = values[1];
                    String subSubCategory = values[2];

                    categoryPresentation.addCategory2(category);
                    categoryPresentation.addSubCategory2(subCategory, category);
                    categoryPresentation.addSubSubCategory2(subSubCategory, subCategory, category);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public void loadProducts(String filePath, ProductPresentation productPresentation) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 11) {
                    String name = values[0];
                    String area = values[1];
                    String manufacturer = values[2];
                    int minQuantity = Integer.parseInt(values[3]);
                    double costPrice = Double.parseDouble(values[4]);
                    double sellingPrice = Double.parseDouble(values[5]);
                    double discount = Double.parseDouble(values[6]);
                    double sale = Double.parseDouble(values[7]);
                    String category = values[8];
                    String subCategory = values[9];
                    String subSubCategory = values[10];

                    productPresentation.addProduct2(name, area, manufacturer, minQuantity, costPrice, sellingPrice, discount, sale, category, subCategory, subSubCategory);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing a number: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public void loadItems(String filePath, ProductPresentation productPresentation) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 3) {
                    String name = values[0];
                    String expirationDate = values[1];
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dateExpirationDate = LocalDate.parse(expirationDate, format);
                    String OnShelf = values[2];
                    boolean boolOnShelf = Boolean.parseBoolean(OnShelf);
                    productPresentation.addItem2(name, dateExpirationDate, boolOnShelf );
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing a number: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }


}
