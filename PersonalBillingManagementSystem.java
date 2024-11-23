import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

class Transaction {
    String date;
    double amount;
    String category;
    String remark;

    public Transaction(String date, double amount, String category, String remark) {
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "日期: " + date + ", 金额: " + amount + ", 类别: " + category + ", 备注: " + remark;
    }
}

public class PersonalBillingManagementSystem {

    private static List<Transaction> incomeList = new ArrayList<>();
    private static List<Transaction> expenseList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    recordIncome();
                    break;
                case 2:
                    recordExpense();
                    break;
                case 3:
                    viewAllBills();
                    break;
                case 4:
                    queryBill();
                    break;
                case 5:
                    running = false;
                    System.out.println("系统已退出。");
                    break;
                default:
                    System.out.println("无效选项，请重新选择。");
            }
        }
    }

    // 显示菜单000
    private static void showMenu() {
        System.out.println("=================================");
        System.out.println("欢迎使用个人账单管理系统");
        System.out.println("=================================");
        System.out.println("请选择操作：");
        System.out.println("1. 记录收入");
        System.out.println("2. 记录支出");
        System.out.println("3. 查看所有账单");
        System.out.println("4. 查询账单");
        System.out.println("5. 退出系统");
        System.out.print("请输入选项序号：");
    }

    // 获取用户输入的选项4848
    private static int getUserChoice() {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("输入无效，请输入数字。");
        }
        return choice;
    }

    // 记录收入
    private static void recordIncome() {
        System.out.println("=================================");
        System.out.println("请输入收入信息：");

        String date = getDateInput();
        double amount = getAmountInput(true);
        System.out.print("类别（如工资、奖金等）：");
        String category = scanner.nextLine();
        System.out.print("备注：");
        String remark = scanner.nextLine();

        incomeList.add(new Transaction(date, amount, category, remark));
        System.out.println("收入已成功记录！");
        promptReturnToMenu();
    }

    // 记录支出
    private static void recordExpense() {
        System.out.println("=================================");
        System.out.println("请输入支出信息：");

        String date = getDateInput();
        double amount = getAmountInput(false);
        System.out.print("类别（如餐饮、交通、购物等）：");
        String category = scanner.nextLine();
        System.out.print("备注：");
        String remark = scanner.nextLine();

        expenseList.add(new Transaction(date, amount, category, remark));
        System.out.println("支出已成功记录！");
        promptReturnToMenu();
    }

    // 获取用户输入的日期，并处理错误
    private static String getDateInput() {
        String date;
        while (true) {
            System.out.print("日期（YYYY-MM-DD）：");
            date = scanner.nextLine();
            try {
                LocalDate.parse(date);  // 验证日期格式是否正确
                break;
            } catch (DateTimeParseException e) {
                System.out.println("日期格式无效，请输入有效的日期格式（YYYY-MM-DD）。");
            }
        }
        return date;
    }

    // 获取用户输入的金额，并处理错误
    private static double getAmountInput(boolean isIncome) {
        double amount = -1;
        while (amount <= 0) {
            System.out.print("金额：");
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount <= 0) {
                    System.out.println("金额必须为正数。");
                }
            } catch (NumberFormatException e) {
                System.out.println("无效的金额，请输入数字。");
            }
        }
        return amount;
    }

    // 提示用户按任意键返回主菜单
    private static void promptReturnToMenu() {
        System.out.println("按任意键返回主菜单...");
        scanner.nextLine();
    }

    // 查看所有账单
    private static void viewAllBills() {
        System.out.println("=================================");
        System.out.println("所有账单：");
        System.out.println("收入：");
        for (Transaction income : incomeList) {
            System.out.println(income);
        }
        System.out.println("支出：");
        for (Transaction expense : expenseList) {
            System.out.println(expense);
        }
        promptReturnToMenu();
    }

    // 查询账单
    private static void queryBill() {
        System.out.print("请输入要查询的日期（YYYY-MM-DD）：");
        String date = scanner.nextLine();
        System.out.println("查询结果：");
        boolean found = false;

        for (Transaction income : incomeList) {
            if (income.date.equals(date)) {
                System.out.println("收入: " + income);
                found = true;
            }
        }

        for (Transaction expense : expenseList) {
            if (expense.date.equals(date)) {
                System.out.println("支出: " + expense);
                found = true;
            }
        }

        if (!found) {
            System.out.println("没有找到该日期的账单。");
        }
        promptReturnToMenu();
    }//zbx是sb
}

