import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] operationsArr = {"Добавить", "Показать", "Удалить"};
        List<String> operations = new ArrayList<>(Arrays.asList(operationsArr)); //список доступных операций
        List<String> purchases = new LinkedList<>(); //список покупок
        Scanner scan = new Scanner(System.in);


        System.out.println("Вам доступны следующие операции: ");
        for (int i = 0; i < operations.size(); i++) {
            System.out.println(i + 1 + ". " + operations.get(i));
        }

        while (true) {
            System.out.println("Выберите операцию и введите ее номер: ");
            String input = scan.nextLine();
            int operationNumber;

            try {
                operationNumber = Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Проверьте корректность ввода данных");
                continue;
            }

            switch (operationNumber) {
                case 1:
                    System.out.println("Какую покупку вы ходите добавить? ");
                    String inputPurchase = scan.nextLine();
                    if (!inputPurchase.trim().equals("")) {
                        purchases.add(capitalize(inputPurchase));
                    }
                    break;
                case 2:
                    purchasePrint(purchases);
                    break;
                case 3:
                    purchasePrint(purchases);
                    System.out.println("Какую хотите удалить? Введите номер или название: ");
                    String inputToDelete = scan.nextLine();
                    int numberToDelete = -1;
                    String purchaseToDelete = null;
                    try {
                        numberToDelete = (Integer.parseInt(inputToDelete.trim()) - 1); //определяем номер покупки к удалению
                    } catch (NumberFormatException exception) {
                        purchaseToDelete = inputToDelete;
                    }

                    if (numberToDelete >= 0 && numberToDelete < purchases.size()) {
                        String deleting = purchases.get(numberToDelete);
                        purchases.remove(numberToDelete);
                        System.out.println("Покупка '" + deleting + "' удалена.");
                        purchasePrint(purchases);
                    } else if (numberToDelete >= purchases.size() || (numberToDelete < 0 && purchaseToDelete == null)) {
                        System.out.println("Покупки с таким номером не существует");
                    } else if (purchaseToDelete != null) {
                        purchaseToDelete = capitalize(purchaseToDelete);
                        Iterator<String> it = purchases.iterator();
                        while (it.hasNext()) {
                            String currentPurchase = it.next();
                            if (currentPurchase.equals(purchaseToDelete)) {
                                it.remove();
                                System.out.println("Покупка '" + purchaseToDelete + "' удалена.");
                                purchasePrint(purchases);
                                break;
                            }
                        }
                        System.out.println("Покупки с таким названием не существует");
                    }

                    break;
                default:
                    System.out.println("Нет операции с таким номером.");
            }
        }


    }

    public static void purchasePrint(List purchases) {
        System.out.println("Список покупок: ");
        for (int i = 0; i < purchases.size(); i++) {
            System.out.println(i + 1 + ". " + purchases.get(i));
        }
    }

    public static String capitalize(String inputPurchase) {
        inputPurchase = inputPurchase.trim().toLowerCase();
        return Character.toUpperCase(inputPurchase.charAt(0)) + inputPurchase.substring(1);

    }
}
