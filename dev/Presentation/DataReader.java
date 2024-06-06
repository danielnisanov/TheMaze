package Presentation;

public class DataReader {

    public static void loadCategories(CategoryPresentation categoryPresentation){
        categoryPresentation.addCategory2("Vegetables");
        categoryPresentation.addSubCategory2("Tomato", "Vegetables");
        categoryPresentation.addSubSubCategory2("100 gram", "Tomato", "Vegetables");

        categoryPresentation.addCategory2("Fruits");
        categoryPresentation.addSubCategory2("Banana", "Fruits");
        categoryPresentation.addSubSubCategory2("150 gram", "Banana", "Fruits");

        categoryPresentation.addCategory2("Fruits");
        categoryPresentation.addSubCategory2("Orange", "Fruits");
        categoryPresentation.addSubSubCategory2("200 gram", "Orange", "Fruits");

        categoryPresentation.addCategory2("Fruits");
        categoryPresentation.addSubCategory2("Apple", "Fruits");
        categoryPresentation.addSubSubCategory2("100 gram", "Apple", "Fruits");

        categoryPresentation.addCategory2("Dairy products");
        categoryPresentation.addSubCategory2("Dairy", "Dairy products");
        categoryPresentation.addSubSubCategory2("1 liter", "Dairy", "Dairy products");

        categoryPresentation.addCategory2("Dairy products");
        categoryPresentation.addSubCategory2("Dairy", "Dairy products");
        categoryPresentation.addSubSubCategory2("1.5 liter", "Dairy", "Dairy products");

        categoryPresentation.addCategory2("Dairy products");
        categoryPresentation.addSubCategory2("Cheese", "Dairy products");
        categoryPresentation.addSubSubCategory2("250 gram", "Cheese", "Dairy products");

        categoryPresentation.addCategory2("Dairy products");
        categoryPresentation.addSubCategory2("Cheese", "Dairy products");
        categoryPresentation.addSubSubCategory2("200 gram", "Cheese", "Dairy products");

        categoryPresentation.addCategory2("Dairy products");
        categoryPresentation.addSubCategory2("Yogurt", "Dairy products");
        categoryPresentation.addSubSubCategory2("250 gram", "Yogurt", "Dairy products");

        categoryPresentation.addCategory2("Eggs products");
        categoryPresentation.addSubCategory2("Eggs", "Eggs products");
        categoryPresentation.addSubSubCategory2("12 Pieces", "Eggs", "Eggs products");

        categoryPresentation.addCategory2("Carbohydrate");
        categoryPresentation.addSubCategory2("Pasta", "Carbohydrate");
        categoryPresentation.addSubSubCategory2("500 gram", "Pasta", "Carbohydrate");


        categoryPresentation.addCategory2("Carbohydrate");
        categoryPresentation.addSubCategory2("Bread", "Carbohydrate");
        categoryPresentation.addSubSubCategory2("1 kg", "Bread", "Carbohydrate");
    }
    public static void  loadProducts(ProductPresentation productPresentation){
        productPresentation.addProduct2("183648449","Sheri tomato","A","Farmers Market",50,0.50,1.00,1,1,"Vegetables","Tomato","100 gram"
        );
        productPresentation.addProduct2("183648440","Yellow banana","A","Fruit Farm",5,0.70,1.00,1,1,"Fruits","Banana","150 gram"
        );
        productPresentation.addProduct2("183648445","Small orange","A","Fruit Farm",3,0.60,1.20,1,1,"Fruits","Orange","200 gram"
        );
        productPresentation.addProduct2("183648442","Red apple","A","Fruit Farm",4,0.70,1.40,1,1,"Fruits","Apple","100 gram"
        );
        productPresentation.addProduct2("183648443","Tnuva milk","B","DairyCo",20,8.00,1.50,1,1,"Dairy products","Dairy","1 liter"
        );
//        productPresentation.addProduct2();
//        productPresentation.addProduct2();
//        productPresentation.addProduct2();
//        productPresentation.addProduct2();
//        productPresentation.addProduct2();
//        productPresentation.addProduct2();
//        productPresentation.addProduct2();
//        productPresentation.addProduct2();


    }

    public static void loadItems(){

    }


}
