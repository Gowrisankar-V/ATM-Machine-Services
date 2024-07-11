import java.util.Scanner;
import java.util.ArrayList;

public class AtmMachine
{
    static int capacity=0;
    static int i;
    static int balance;
    static int choice;
    static int yesNo;
    public static ArrayList<String> listCard=new ArrayList<>();
    public static ArrayList<Integer> listPin=new ArrayList<>();
    public static ArrayList<Integer> listBalance=new ArrayList<>();
    public static void main(String[] args)
    {
        System.out.println();
        System.out.println("=======================================================================");
        System.out.println();
        System.out.println("                             K.S.R  ATM");
        System.out.println();
        System.out.println("=======================================================================");
        System.out.println();
        int deposit=10000;
        int pin=1001;
        for(i=1;i<=10;i++)
        {
            listCard.add("card"+i);
            if(i<10000)
            {
                listPin.add(pin);
            }
            listBalance.add(deposit);
            deposit+=10000;
            pin++;
        }
        for(int sum:listBalance)
        {
            capacity=capacity+sum;
        }
        System.out.println("For Details here are the Card Names & their Corresponding Pin Numbers ");
        System.out.println();
        System.out.println(listCard);
        System.out.println(listPin);
        System.out.println();
        authentication();
    }

    public static void authentication()
    {
        Scanner in=new Scanner(System.in);
        System.out.print("Enter your Card Name : ");
        String cardName=in.nextLine().toLowerCase();
        System.out.print("Enter your Pin Number : ");
        try
        {
            int pinNumber=in.nextInt();
            System.out.println();
            if(listCard.contains(cardName) && listPin.contains(pinNumber) && listCard.indexOf(cardName)==listPin.indexOf(pinNumber))
            {
                System.out.println("----Authentication Successful----");
                System.out.println();
            }
            else
            {
                System.out.println("-------Authentication Failed------");
                System.out.println();
                authentication();
            }
            services(cardName,pinNumber);
        }
        catch(Exception e)
        {
            System.out.println();
            System.out.println("---------Authentication Failed--------");
            System.out.println();
            authentication();
        }
    }

    public static void services(String cardName,int pinNumber)
    {
        System.out.println("--------Welcome to Our K.S.R ATM--------");
        option(cardName,pinNumber);
    }

    public static void option(String cardName,int pinNumber)
    {
        Scanner in=new Scanner(System.in);
        System.out.println();
        System.out.println("-------------Select Your Option-----------");
        System.out.println();
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Deposit Money");
        System.out.println("4. Exit");
        System.out.println();
        System.out.print("Enter your choice : ");
        try
        {
            choice=in.nextInt();
            System.out.println();

            switch(choice)
            {
                case 1:
                    checkBalance(cardName, pinNumber);
                    break;
                case 2:
                    if(capacity!=0)
                    {
                        cashWithdraw(cardName,pinNumber);
                    }
                    else
                    {
                        exit();
                    }
                    break;
                case 3:
                    cashDeposit(cardName,pinNumber);
                    break;
                case 4:
                    exit();
                    break;
                default:
                    System.out.println("--------Invalid Choice--------");
                    option(cardName, pinNumber);
                    break;
            }
        }
        catch(Exception e)
        {
            System.out.println("-------------Invalid Choice-----------");
            System.out.println();
            option(cardName, pinNumber);
        }
    }

    public static void checkBalance(String cardName,int pinNumber)
    {
        Scanner in=new Scanner(System.in);
        System.out.println("----------------Your Balance--------------");
        System.out.println();
        balance=listBalance.get(listCard.indexOf(cardName));
        System.out.println("                    Rs: "+balance);
        System.out.println();
        System.out.println("------------------------------------------");
        System.out.println();
        System.out.println("Do you want to Exit ? \n1) Yes \n2) No");
        System.out.println();
        try
        {
            yesNo=in.nextInt();
            System.out.println();
            switch(yesNo)
            {
                case 1:
                    exit();
                    break;
                case 2:
                    System.out.println();
                    services(cardName, pinNumber);
                    break;
                default:
                    System.out.println();
                    System.out.println("Thank You for Using Our ATM");
                    System.out.println();
                    break;
            }
        }
        catch(Exception e)
        {
            System.out.println("-------------Invalid Choice-----------");
            System.out.println();
            checkBalance(cardName, pinNumber);
        }
    }

    public static void cashWithdraw(String cardName,int pinNumber)
    {
        Scanner in=new Scanner(System.in);
        System.out.print("Enter the amount you want to withdraw : ");
        int amountWithdraw=in.nextInt();
        System.out.println();
        if(amountWithdraw%100==0)
        {
            if(amountWithdraw>listBalance.get(listCard.indexOf(cardName)))
            {
                System.out.println("-------Insufficient Balance-------");
                System.out.println();
                System.out.println("Your Available Balance : "+listBalance.get(listCard.indexOf(cardName)));
                System.out.println();
                System.out.println("----------------------------------");
                cashWithdraw(cardName,pinNumber);
            }
            else
            {
                listBalance.set(listCard.indexOf(cardName),listBalance.get(listCard.indexOf(cardName))-amountWithdraw);
                System.out.println("-----Cash Withdraw Successful-----");
                capacity=capacity-amountWithdraw;
                System.out.println();
                System.out.println("Your Available Balance is : "+listBalance.get(listCard.indexOf(cardName)));
                System.out.println();
                System.out.println("----------------------------------");
            }
        }
        else
        {
            System.out.println("------------Invalid Amount------------");
            System.out.println();
            System.out.println("--------------------------------------");
            System.out.println();
            System.out.println("Amount Only in \n \n--> 100 \n--> 200 \n--> 500 \n--> 2000");
            System.out.println();
            cashWithdraw(cardName,pinNumber);
        }
        System.out.println();
        System.out.println("Do you want to Exit ? \n1) Yes \n2) No");
        System.out.println();
        try
        {
            yesNo=in.nextInt();
            System.out.println();
            switch(yesNo)
            {
                case 1:
                    exit();
                    break;
                case 2:
                    System.out.println();
                    services(cardName, pinNumber);
                    break;
                default:
                    System.out.println();
                    System.out.println("Thank You for Using Our ATM");
                    System.out.println();
                    break;
            }
        }
        catch(Exception e)
        {
            System.out.println("-------------Invalid Choice-----------");
            System.out.println();
            cashWithdraw(cardName, pinNumber);
        }
    }

    public static void cashDeposit(String cardName,int pinNumber)
    {
        Scanner in=new Scanner(System.in);
        System.out.println("------Insert your Amount into the ATM-----");
        System.out.println();
        System.out.println("------------------------------------------");
        System.out.println();
        System.out.println("Amount Only in \n \n--> 100 \n--> 200 \n--> 500 \n--> 2000 \n");
        System.out.println();
        System.out.print("Enter your Inserted Amount : ");
        int amountDeposit=in.nextInt();
        System.out.println();
        if(amountDeposit%100==0)
        {
            System.out.println("--------------------------------------");
            listBalance.set(listCard.indexOf(cardName),listBalance.get(listCard.indexOf(cardName))+amountDeposit);
            System.out.println();
            System.out.println("----------Deposit Successful----------");
            capacity=capacity+amountDeposit;
            System.out.println();
            System.out.println("Your Balance is : "+listBalance.get(listCard.indexOf(cardName)));
            System.out.println();
            System.out.println("--------------------------------------");
        }
        else
        {
            System.out.println("Invalid Amount");
            System.out.println();
            System.out.println("--------------------------------------");
            cashDeposit(cardName,pinNumber);
        }
        System.out.println();
        System.out.println("Do you want to Exit ? \n1) Yes \n2) No");
        System.out.println();
        try
        {
            yesNo=in.nextInt();
            System.out.println();
            switch(yesNo)
            {
                case 1:
                    exit();
                    break;
                case 2:
                    System.out.println();
                    services(cardName, pinNumber);
                    break;
                default:
                    System.out.println();
                    System.out.println("Thank You for Using Our ATM");
                    System.out.println();
                    break;
            }
        }
        catch(Exception e)
        {
            System.out.println("-------------Invalid Choice-----------");
            System.out.println();
            cashDeposit(cardName, pinNumber);
        }
    }

    public static void exit()
    {
        System.out.println();
        System.out.println("Thank You for Using Our ATM");
        System.out.println();
        if(capacity==0)
        {
            System.out.println("======================================");
            System.out.println();
            System.out.println("       ATM is Under Maintenance");
            System.out.println();
            System.out.println("======================================");
        }
        else if(yesNo==1||choice==4)
        {
           return;
        }
        else
        {
            System.out.println("-------------------------------------");
            authentication();
        }
        System.out.println();
    }
}
