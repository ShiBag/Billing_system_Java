import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class BillingSystem {
    public static String encrypt(String fname) {
        char[] ch = new char[fname.length()];
        int index = 0;
        for (int i = 0; i < fname.length(); i++) {
            ch[index++] = (char) ((int) fname.charAt(i) + 1);
        }
        String s = new String(ch);
        return s;
    }

    public static String decrypt(String fname) {
        char[] ch = new char[fname.length()];
        int index = 0;
        for (int i = 0; i < fname.length(); i++) {
            ch[index++] = (char) ((int) fname.charAt(i) - 1);
        }
        String s = new String(ch);
        return s;
    }

    public static void main(String args[]) throws IOException {

        try {
            Scanner sc = new Scanner(System.in);
            File f1 = new File("admin_details.txt");
            Scanner read1 = new Scanner(f1);

            System.out.println("Enter Username :");
            String Username = sc.next();

            if (encrypt(Username).equals(read1.nextLine())) {
                System.out.println("Enter Password :");
                String pass = sc.next();
                if (encrypt(pass).equals(read1.nextLine())) {
                    System.out.println("WELCOME ADMIN:");
                    System.out.println("ENTER 1 TO TAKE ORDER, 2 TO UPDATE ADMIN CREDENTIALS :");
                    int i = sc.nextInt();
                    if(i==1){
                    int re = 1;
                    while(re == 1){
                        re = 0;
                    
                    Calendar calendar = Calendar.getInstance();
                    String folderName = new SimpleDateFormat("MMMM").format(calendar.getTime());
                    // CREATING 'MENU' FILE
                    File folder = new File(folderName);
                    if (!folder.exists()) {
                        folder.mkdir();
                    }
                    File menu = new File("./menu.txt");

                    // WRITING TO FILE 'MENU'

                    FileWriter write_menu = new FileWriter("./menu.txt");
                    write_menu.write("Idli 10\n");
                    write_menu.write("Dosa 32\n");
                    write_menu.write("Roti 7\n");
                    write_menu.write("Rice 12\n");
                    write_menu.write("Dal 12");

                    write_menu.close();

                    // TAKING CUSTOMER ORDER
                    // NAME
                    Scanner scan_name = new Scanner(System.in);
                    System.out.println("Enter Name: ");
                    String name = scan_name.nextLine();

                    // ITEM
                    int sn = 1;
                    Scanner scan_item = new Scanner(System.in);
                    Scanner scan_quantity = new Scanner(System.in);

                    File billNumber = new File("./bill_no.txt");
                    if (billNumber.createNewFile()) {
                        System.out.println("Please Enter You Order");
                    } else {
                        System.out.println("Please Enter You Order");
                    }
                    int billdata = 0;
                    System.out.println("Here is the Menu");
                    System.out.println("................");
                    System.out.println("Idli -Rs 10\n");
                    System.out.println("Dosa -Rs 32\n");
                    System.out.println("Roti -Rs 17\n");
                    System.out.println("Rice -Rs 32\n");
                    System.out.println("Dal  -Rs 32\n");

                    Scanner billread = new Scanner(billNumber);
                    while (billread.hasNextLine()) {
                        billdata = Integer.parseInt(billread.next());
                    }
                    FileWriter billn_write = new FileWriter(billNumber);
                    billn_write.write(String.valueOf(billdata + 1));
                    billread.close();
                    billn_write.close();

                    // CREATING BILL TEMPLATE
                    File bill = new File("./" + folderName + "/bill_" + String.valueOf(billdata + 1) + ".txt");
                    FileWriter write_bill = new FileWriter(bill);
                    // writing the content into the FileOperationExample.txt file
                    write_bill.write("........................................\n");
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date();
                    write_bill.write(".............BILL NUMBER :" + String.valueOf(billdata + 1) + "...........\n");
                    write_bill.write("WELCOME " + name + "      " + formatter.format(date) + "\n");

                    write_bill.write("........................................\n");
                    write_bill.write("                  BILL                  \n");
                    write_bill.write("........................................\n");
                    write_bill.write("|    ITEM    |     COST    |     QTY   |\n");
                    write_bill.write("........................................\n");
                    int total = 0;
                    int quantity;
                    while (true) {
                        System.out.print("Enter item name (type 'quit' to exit)(type 'redo' to reorder): ");
                        String itemName = scan_item.nextLine();
                        if(itemName.equals("redo")){
                            re = 1;
                            FileWriter fr1 = new FileWriter("bill_no.txt");
                            fr1.write(String.valueOf(billdata));
                            fr1.close();
                            break;
                        }
                        if (itemName.equals("quit")) {
                            break;
                        }
                        System.out.print("Enter Quantity  (Enter -1 to reorder)");
                        quantity = scan_quantity.nextInt();
                        if(quantity == -1){
                            re = 1;
                            FileWriter fr1 = new FileWriter("bill_no.txt");
                            fr1.write(String.valueOf(billdata));
                            fr1.close();
                            System.out.println(billdata);
                            break;
                        }
                        Scanner menuReader = new Scanner(menu);
                        while (menuReader.hasNextLine()) {

                            String fileData = menuReader.next();

                            if (fileData.equals(itemName)) {
                                int cost = Integer.parseInt(menuReader.next());
                                write_bill.write(sn + ".    " + fileData + "     " + "     " + cost + "      " + "     "
                                        + quantity + "      \n");
                                write_bill.write("........................................\n");
                                total = total + cost * quantity;
                            }
                        }
                        sn = sn + 1;
                        menuReader.close();
                    }
                    if(re == 0){
                    double tax = 0.2 * total;
                    double grand_total = total + tax;
                    write_bill.write("                          TOTAL: " + total + "\n");
                    write_bill.write("               TAX (20 Percent): " + Math.round(tax) + "\n");
                    write_bill.write("                    GRAND TOTAL: " + grand_total + "\n");
                    write_bill.write("........................................\n");
                    write_bill.write("               |THANK YOU!|             \n");
                    write_bill.write("      |YOUR ORDER HAS BEEN PLACED!|     \n");
                    write_bill.write("........................................\n");
                    write_bill.close();

                    scan_name.close();
                    scan_item.close();
                    scan_quantity.close();
                    }
                }
            }
                else if(i==2){
                    Scanner read2 = new Scanner(f1);
                    Scanner cred = new Scanner(System.in);
                    System.out.println("Enter current username");
                    String user = cred.nextLine();
                    System.out.println("Enter current password");
                    String pwd = cred.nextLine();
                    while(!(user.equals(decrypt(read2.nextLine())) && pwd.equals(decrypt(read2.nextLine())))){
                        System.out.println("Invalid Credentials.Please re-enter username and password...");
                        user = cred.nextLine();
                        pwd = cred.nextLine();
                        read2.close();
                        read2 = new Scanner(f1);
                    }
                    System.out.println("Enter new username");
                    String nUser = cred.nextLine();
                    System.out.println("Enter new password");
                    String nPwd = cred.nextLine();
                    FileWriter fw = new FileWriter(f1);
                    fw.write(encrypt(nUser));
                    fw.write("\n");
                    fw.write(encrypt(nPwd));
                    fw.close();
                    System.out.println("Successfullu Updated Admin Credentials");
                }
                
            } 
            else {
                System.out.println("Wrong password");
            }
        }
            else {
                System.out.println("Wrong username");
            }
                    
                    
        }

        catch (FileNotFoundException exception) {
            System.out.println("Unexcpected error occurred!");
            exception.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }

    }

}