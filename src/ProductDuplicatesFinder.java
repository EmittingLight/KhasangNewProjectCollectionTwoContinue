import java.util.*;

public class ProductDuplicatesFinder {
    private final int NUMBER_OF_PRODUCTS = 100000;
    private List<ProductWrapper> list1 = new ArrayList<>(NUMBER_OF_PRODUCTS);
    private List<ProductWrapper> list2 = new ArrayList<>(NUMBER_OF_PRODUCTS);
    private Random random = new Random();

    public void findDuplicateProductsAndPrintResults() {
        System.out.println("Инициализация данных");
        System.out.println();
        initializeProductData();
        generateSecondaryProductData();
        System.out.println("====== Поиск дубликатов без использования хэш-таблицы: списки сравниваются элемент за элементом. ======");
        findDuplicateProducts(list1, list2);
        System.out.println();
        System.out.println("====== Поиск дубликатов (с использованием списка)");
        findDuplicateProductsAndPrintExecutionTime(list1, list2);
    }

    private void initializeProductData() {
        for (int i = 0; i < NUMBER_OF_PRODUCTS; i++) {
            Product product = new Product();
            product.setCategory(getRandomProductCategory());
            product.setInternalCode(getInternalCode());
            product.setName(generatePrefixedName(i));
            list1.add(new ProductWrapper(product));
        }
    }

    private void generateSecondaryProductData() {
        for (int i = 0; i < NUMBER_OF_PRODUCTS; i++) {
            ProductWrapper product;
            if (i % 100 == 0) {
                product = list1.get(i);
            } else {
                Product newProduct = new Product();
                newProduct.setCategory(getRandomProductCategory());
                newProduct.setInternalCode(getInternalCode());
                newProduct.setName(generatePrefixedName(i));
                product = new ProductWrapper(newProduct);
            }
            list2.add(product);
        }
    }

    private String getRandomProductCategory() {
        String[] categories = {"Electronics", "Home Decor", "Sports Equipment"};
        int index = random.nextInt(categories.length);
        return categories[index];
    }

    private String generatePrefixedName(int modifier) {
        String[] results = {"Great option", "Standard option", "Poor option"};
        int randomIndex = random.nextInt(results.length);
        return results[randomIndex] + modifier;
    }

    private byte[] getInternalCode() {
        byte[] result = new byte[16];
        random.nextBytes(result);
        return result;
    }

    private void findDuplicateProducts(List<ProductWrapper> list1, List<ProductWrapper> list2) {
        if (list1 == null || list1.isEmpty() || list2 == null || list2.isEmpty()) {
            System.out.println("Одна из переданных коллекций пуста!");
            return;
        }

        long startStamp = System.currentTimeMillis();
        List<ProductWrapper> duplicates = new ArrayList<>();

        for (ProductWrapper product : list1) {
            if (list2.contains(product)) {
                duplicates.add(product);
            }
        }

        System.out.println("Количество дубликатов: " + duplicates.size());
        printExecutionTime(startStamp);
    }

    private void findDuplicateProductsAndPrintExecutionTime(List<ProductWrapper> list1, List<ProductWrapper> list2) {
        if(list1 == null || list1.isEmpty() || list2 == null || list2.isEmpty()) {
            System.out.println("Одна из переданных коллекций пуста!");
            return;
        }
        long startStamp = System.currentTimeMillis();
        Map<ProductWrapper, Integer> map = new HashMap<>();

        for (ProductWrapper product : list1) {
            map.put(product, 1);
        }

        List<ProductWrapper> duplicates = new ArrayList<>();

        for (ProductWrapper product : list2) {
            Integer value = map.get(product);
            if (value != null) {
                duplicates.add(product);
            }
        }

        System.out.println("Количество дубликатов: " + duplicates.size());
        printExecutionTime(startStamp);
    }

    private void printExecutionTime(long startStamp) {
        long endStamp = System.currentTimeMillis();
        long executionTime = endStamp - startStamp;
        System.out.println("Время выполнения (мс): " + executionTime);
    }
}

