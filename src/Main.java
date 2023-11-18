import java.util.*;
import java.io.*;
/*
 * name
 * lastname
 * Account ID
 * ID
 * Balance
 * Password
 * Birthday
 * */

public class Main {

    public static void main(String[] args) {//Execute our main class for the "Main Menu"
        WelcomeProcedure welcome = new WelcomeProcedure();
    }
}

class WelcomeProcedure {
    WelcomeProcedure() {
        System.out.println("Welcome to Maze Bank ATM");//Welcome message
        System.out.println("1.Sign in as attendant\n2.Sign in as user\n3.Exit");//Main Menu
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                Attendant attendant = new Attendant();//Execute class for Attendant
                break;
            case 2:
                User user = new User();//Execute class for User
                break;
            case 3:
                System.exit(5);//Exiting with code 5 from Main Menu
                break;
            default:
                System.exit(55);
        }
    }
}

class Attendant {
    public Attendant() {
        SignInAttendant credentials = new SignInAttendant();//Using SignInAttendant class
        boolean check = credentials.verify(credentials.getUserName(), credentials.getPassword());//Method verify from SignInAttendant class
        if (check) {//Username: admin ****** Password: 1988
            System.out.println("Welcome Fellow Attendant");//Attendant Menu
            System.out.println("1.Open an account\n2.Update an account\n3.Back to main menu");
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    CreatAccount creatNewAccount = new CreatAccount();
                    break;
                case 2:
                    UpdateAccount updateAccount = new UpdateAccount();
                    break;
                case 3:
                    WelcomeProcedure welcome = new WelcomeProcedure();
                    break;
                default: System.exit(55);
            }
        } else {
            System.out.println("username or password is incorrect");
        }
    }
}

class User {
    public User() {
        SignInUser signInUser = new SignInUser();
        Scanner input = new Scanner(System.in);
        System.out.println("Please Enter Username: ");
        String userName = input.nextLine();
        System.out.println("Please Enter Password: ");
        int password = input.nextInt();

        boolean check = signInUser.verify(userName,password);
        if (check) {
            System.out.println("Welcome User");
            System.out.println("1.Account balance\n2.Cash transfer\n3.Cash withdrawal\n4.Recent Account Update");
            System.out.println("5.Change Password\n6.Back to main menu");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    CheckBalance checkBalance = new CheckBalance();
                    checkBalance.showBalance(userName);
                    break;
                case 2:
                    MoneyTransfer moneyTransfer = new MoneyTransfer();
                    moneyTransfer.transfer(userName);
                    break;
                case 3:
                    CashWithdrawal cashWithdrawal = new CashWithdrawal();
                    cashWithdrawal.withdraw(userName);
                    break;
                case 4:
                    RecentAccountUpdates recentAccountUpdates = new RecentAccountUpdates();
                    recentAccountUpdates.showAccountUpdates(userName);
                    break;
                case 5:
                    ChangePassword changePassword = new ChangePassword(userName);
                    break;
                case 6:
                    WelcomeProcedure welcome = new WelcomeProcedure();
                    break;
                default: System.exit(55);
            }
        } else {
            System.out.println("Username or password is incorrect");
        }
    }
}

//defining an interface for sign in
interface ISignIn {
    boolean verify(String username, int password);
}

//implementation from aforementioned interface for user
class SignInUser implements ISignIn {
    @Override
    public boolean verify(String username, int password) {
        int verificationLevel = 0;
        File file = new File("D:\\");
        File[] files = file.listFiles();
        assert files != null;//idk what this is for now
        for (File temp: files) {
            if (temp.isFile() && temp.getName().equals(username+".txt")) {
                ++verificationLevel;
                break;
            }
        }
        if (verificationLevel == 1) {
            ArrayList<String> listLines = new ArrayList<String>();
            FileToArrayList fileToArrayList = new FileToArrayList();
            listLines = fileToArrayList.convertFile("D:\\"+username+".txt");
            if ( listLines.get(5).equals(Integer.toString(password)) ) {
                return true;
            }
        }
        return false;
    }
}

//implementation from aforementioned interface for Attendant
class SignInAttendant implements ISignIn {
    private String userName;
    private int password;

    public String getUserName() {
        return userName;
    }

    public int getPassword() {
        return password;
    }

    public SignInAttendant() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter username: ");
        this.userName = input.nextLine();
        System.out.print("Please enter password: ");
        this.password = input.nextInt();
    }

    @Override
    public boolean verify(String userName,int password) {
        if (userName.equals("admin") && password == 1988) {
            return true;
        } else {
            return false;
        }
    }
}

class Account {
    private int accountId,id,balance,password;
    private String name,lastName;
    //Could've used MAP for birthday but I chose array
    private int[] birthday = new int[3];
    //Birthday is DD/MM/YYYY

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int[] getBirthday() {
        return birthday;
    }

    public void setBirthday(int[] birthday) {
        this.birthday = birthday;
    }
}


//Might add a different class for creating the file
class CreatAccount {
    public CreatAccount() {
        ArrayList<String> listLine = new ArrayList<String>();

        Scanner input = new Scanner(System.in);
        System.out.println("Please Enter Name: ");
        listLine.add(input.nextLine());

        System.out.println("Please Enter last Name: ");
        listLine.add(input.nextLine());

        System.out.println("Please Enter Account ID: ");
        listLine.add(input.nextLine());

        System.out.println("Please Enter ID: ");
        listLine.add(input.nextLine());

        System.out.println("Please Enter Initialized Balance: ");
        listLine.add(input.nextLine());

        System.out.println("Please Enter Password: ");
        listLine.add(input.nextLine());

        System.out.println("Please Enter Birthday Date with space (Example: DD MM YYYY): ");
        listLine.add(input.nextLine());
        CreateFile createFile = new CreateFile(listLine);
    }
}

//Converts files to ArrayList
class FileToArrayList {
    public ArrayList<String> convertFile(String filePath) {
        //copied from web
        BufferedReader lineReader = null;
        try {
            lineReader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String lineText = null;

        ArrayList<String> listLines = new ArrayList<String>();

        while (true) {
            try {
                if (!((lineText = lineReader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            listLines.add(lineText);
        }

        try {
            lineReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listLines;
    }
}

class UpdateAccount {//must delete the original file too
    public UpdateAccount() {
        Scanner input = new Scanner(System.in);
        System.out.println("Which Account ID do you want to update: ");
        int accId = input.nextInt();
        ArrayList<String> listLines = new ArrayList<String>();
        FileToArrayList fileToArrayList = new FileToArrayList();
        listLines = fileToArrayList.convertFile("D:\\"+accId+".txt");

        //Fixing log file bugs here
        ArrayList<String> listLinesLog = new ArrayList<String>();
        FileToArrayList fileToArrayListLog = new FileToArrayList();
        listLinesLog = fileToArrayListLog.convertFile("D:\\"+accId+"logs"+".txt");
        DeleteFile deleteLogFile = new DeleteFile("D:\\"+accId+"logs"+".txt");
        //*************************

        boolean condition = true;
        while (condition) {
            System.out.println("Which section do you aim to change?");
            System.out.println("1.Name\n2.Last Name\n3.Account ID\n4.ID\n5.Balance\n6.Password\n7.Birthday");
            int choice = input.nextInt();
            input.nextLine();//hama error, because of nextInt()
            switch (choice) {
                case 1:
                    System.out.println("Update name: ");
                    listLines.set(0,input.nextLine());
                    break;
                case 2:
                    System.out.println("Update Last name: ");
                    listLines.set(1,input.nextLine());
                    break;
                case 3:
                    System.out.println("Update Account ID: ");
                    listLines.set(2,input.nextLine());
                    break;
                case 4:
                    System.out.println("Update ID: ");
                    listLines.set(3,input.nextLine());
                    break;
                case 5:
                    System.out.println("Update Balance: ");
                    listLines.set(4,input.nextLine());
                    break;
                case 6:
                    System.out.println("Update Password: ");
                    listLines.set(5,input.nextLine());
                    break;
                case 7:
                    System.out.println("Update Birthday (Example: DD MM YYYY): ");
                    listLines.set(6,input.nextLine());
                    break;
                default:
                    System.out.println("Number entered was invalid");
            }
            System.out.println("Are You Done?(y/n)");
            String strChoice = input.next();

            //Ask if they wanna keep updating
            condition = !strChoice.equals("y");

        }

        DeleteFile deleteFile = new DeleteFile("D:\\"+accId+".txt");
        CreateFile createFile = new CreateFile(listLines);
        CreatLogFile logFile = new CreatLogFile();
        logFile.justNewLogContent(listLines.get(2),listLinesLog);

    }
} // log checked

class DeleteFile {

    public DeleteFile(String filepath) {
        File file = new File(filepath);
        if(file.delete()) {
            System.out.println("File deleted successfully");
        }
        else {
            System.out.println("Failed to delete the file");
            System.exit(420);
        }
    }
}

class CreateFile {
    public CreateFile(ArrayList<String> listLine) {
        try{

            FileWriter myWriter = new FileWriter("D:/"+listLine.get(2)+".txt",true);
            myWriter.write(listLine.get(0));
            myWriter.write("\n"+listLine.get(1));
            myWriter.write("\n"+listLine.get(2));
            myWriter.write("\n"+listLine.get(3));
            myWriter.write("\n"+listLine.get(4));
            myWriter.write("\n"+listLine.get(5));
            myWriter.write("\n"+listLine.get(6));
            myWriter.close();

        } catch (IOException e){
            System.out.println("An error occurred.");
        }
        CreatLogFile logFile = new CreatLogFile();
        logFile.justNewLog(listLine.get(2));
    }
} // log checked

class CheckBalance {
    public void showBalance(String accountId) {
        FileToArrayList fileToArrayList = new FileToArrayList();
        ArrayList<String> listLines = new ArrayList<String>();
        listLines = fileToArrayList.convertFile("D:\\"+accountId+".txt");
        System.out.println(listLines.get(4));
    }
}

class CashWithdrawal {
    public void withdraw(String accountId) {
        FileToArrayList fileToArrayList = new FileToArrayList();
        ArrayList<String> listLines = new ArrayList<String>();
        listLines = fileToArrayList.convertFile("D:\\"+accountId+".txt");
        System.out.println("How much money do you wish to withdraw: ");
        Scanner input = new Scanner(System.in);
        int deductionAmount = input.nextInt();
        int currentBalance;
        try {
            currentBalance = Integer.parseInt(listLines.get(4));
        }
        catch (NumberFormatException e)
        {
            currentBalance = 0;
        }
        int newBalance = currentBalance - deductionAmount;
        listLines.set(4, Integer.toString(newBalance));
        DeleteFile deleteFile = new DeleteFile("D:\\"+accountId +".txt");
        CreateFile createFile = new CreateFile(listLines);
        //Creat a log file for withdrawal
        CreatLogFile creatLogFile = new CreatLogFile();
        creatLogFile.newLog(accountId,"Withdrawal", Integer.toString(deductionAmount));
        System.out.println("Cash Withdrawal was successful");

    }
} //log checked

class ChangePassword {
    public ChangePassword(String accountId) {
        String newPassword="",checkOldPassword;
        ArrayList<String> listLines = new ArrayList<String>();
        FileToArrayList fileToArrayList = new FileToArrayList();
        listLines = fileToArrayList.convertFile("D:\\"+accountId+".txt");
        Scanner input = new Scanner(System.in);

        int count = 0;
        while (count < 5) {
            if (count == 4) {
                System.out.println("You've tried 4 times, your account will be deleted");
                DeleteFile deleteMainFile = new DeleteFile("D:\\"+accountId+".txt");
                DeleteFile deleteLogFile = new DeleteFile("D:\\"+accountId+"logs"+".txt");
                System.exit(6969);
            }
            System.out.println("Enter old password: ");
            checkOldPassword = input.next();
            if (listLines.get(5).equals(checkOldPassword)) {
                break;
            } else {
                count++;
                System.out.println("password was incorrect, do you want to try again?(y/n): ");
                String tryAgain = input.next();
                if (tryAgain.equals("n")) {
                    System.exit(70);
                    break;
                }
            }

        }
        count = 0;
        while (count < 5) {
            if (count == 4) {
                System.out.println("You've tried 4 times, your account will be deleted");
                DeleteFile deleteMainFile = new DeleteFile("D:\\"+accountId+".txt");
                DeleteFile deleteLogFile = new DeleteFile("D:\\"+accountId+"logs"+".txt");
                System.exit(6969);
            }
            System.out.println("Please Enter your new password: ");
            newPassword = input.next();
            if (listLines.get(5).equals(newPassword)) {
                ++count;
                System.out.println("Your new password cannot be the same as your old password\nDo you want to try again?:(y/n) ");
                String tryAgain = input.next();
                if (tryAgain.equals("n")) {
                    System.exit(70);
                    break;
                }
            } else {
                break;
            }
        }
        listLines.set(5,newPassword);
        DeleteFile deleteFile = new DeleteFile("D:\\"+accountId+".txt");
        CreateFile createFile = new CreateFile(listLines);

    }
} //log checked

class CreatLogFile {
    public void newLog(String accountId, String logKind, String amount) {
        Date d = new Date();
        try{
            FileWriter myWriter = new FileWriter("D:/"+accountId+"logs"+".txt",true);
            myWriter.write(amount + " " + logKind + " at "+ d + System.lineSeparator());
            myWriter.close();

        } catch (IOException e){
            System.out.println("An error occurred.");
        }
    }
    public void justNewLog(String accountId) {
        try{
            FileWriter myWriter = new FileWriter("D:/"+accountId+"logs"+".txt",true);
            myWriter.close();

        } catch (IOException e){
            System.out.println("An error occurred.");
        }
    }
    public void justNewLogContent(String accountId,ArrayList<String> listLinesLog) {
        try{
            FileWriter myWriter = new FileWriter("D:/"+accountId+"logs"+".txt",true);
            for (String str: listLinesLog) {
                myWriter.write(str + System.lineSeparator());
            }
            myWriter.close();

        } catch (IOException e){
            System.out.println("An error occurred.");
        }
    }

    public void transferLog(String sender, String receiver,String amount) {
        Date d = new Date();
        try{
            FileWriter myWriter = new FileWriter("D:/"+sender+"logs"+".txt",true);
            myWriter.write(amount + " was transferred to "+ receiver +  " at "+ d + System.lineSeparator());
            myWriter.close();

        } catch (IOException e){
            System.out.println("An error occurred.");
        }
        try{
            FileWriter myWriter = new FileWriter("D:/"+receiver+"logs"+".txt",true);
            myWriter.write(amount + " was received from "+ sender +  " at "+ d + System.lineSeparator());
            myWriter.close();

        } catch (IOException e){
            System.out.println("An error occurred.");
        }
    }
}

class RecentAccountUpdates {
    public void showAccountUpdates (String accountId) {
        ArrayList<String> logArray = new ArrayList<String>();
        FileToArrayList fileToArrayListLog = new FileToArrayList();
        logArray = fileToArrayListLog.convertFile("D:\\"+accountId+"logs"+".txt");
        if (logArray.size() < 10) {
            for (int i = 0; i < logArray.size(); i++) {
                System.out.println(logArray.get(i));
            }
        } else {
            for (int i = 0; i < 10; i++) {
                System.out.println(logArray.get(i));
            }
        }
    }
}

class MoneyTransfer {
    public void transfer (String sender) {
        Scanner input = new Scanner(System.in);
        String receiver = "";
        int counter = 0;
        while (counter < 5) {
            if (counter == 4) {
                System.out.println("You've entered wrong account number for 4 times, you will be transferred to main menu...");
                WelcomeProcedure welcomeProcedure2 = new WelcomeProcedure();
            }
            System.out.println("Which account do you want to transfer your money to: ");
            receiver = input.next();

            File receiverFile = new File("D:\\");
            File[] files = receiverFile.listFiles();
            boolean check = false;
            assert files != null;
            for (File temp: files) {
                if (temp.isFile() && temp.getName().equals(receiver+".txt")) {
                    check = true;
                    break;
                }
            }

            if (check) {
                break;
            } else {
                ++counter;
                System.out.println("Such account number does not exist, do you wish to enter it again:(y/n) ");
                String choice = input.next();
                if (choice.equals("n")) {
                    WelcomeProcedure welcomeProcedure = new WelcomeProcedure();
                }
            }
        }
        System.out.println("How much money do you wish to transfer: ");
        int amount = input.nextInt();
        ArrayList<String> receiverArrayList = new ArrayList<String>();
        ArrayList<String> senderArrayList = new ArrayList<String>();
        FileToArrayList fileToArrayList = new FileToArrayList();
        receiverArrayList = fileToArrayList.convertFile("D:\\"+ receiver + ".txt");
        senderArrayList = fileToArrayList.convertFile("D:\\"+ sender + ".txt");

        int receiverBalance = amount + Integer.parseInt(receiverArrayList.get(4));
        int senderBalance = Integer.parseInt(senderArrayList.get(4)) - amount;

        receiverArrayList.set(4,Integer.toString(receiverBalance));
        senderArrayList.set(4,Integer.toString(senderBalance));

        DeleteFile deleteReceiverMain = new DeleteFile("D:\\"+receiver+".txt");
        DeleteFile deleteSenderMain = new DeleteFile("D:\\"+sender+".txt");

        CreateFile createReceiverFile = new CreateFile(receiverArrayList);
        CreateFile createSenderFile = new CreateFile(senderArrayList);

        CreatLogFile transferLog = new CreatLogFile();
        transferLog.transferLog(sender,receiver,Integer.toString(amount));

    }
}