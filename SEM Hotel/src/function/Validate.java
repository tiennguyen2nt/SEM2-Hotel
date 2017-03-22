/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

/**
 *
 * @author Tien Nguyen
 */
public class Validate {

   

    //Exception -------------------------------------------------------------
    //Check ID xem có đúng định dạng không | nếu id đúng định dạng thì trả về true;
    public boolean checkFormatID(String id) {
        boolean check = false;

        //kiểm tra xem ID có đúng định dạng không? ID chỉ chứa các ký tự từ a -> z, A -> Z và 0 -> 9 độ dài = 10 
        if (id.matches("^[a-zA-Z0-9](\\w{4,24})$")) {
            return true;
        } else {
            System.out.println("   ! Bạn nhập sai định dạng ID. ID chỉ chứa các ký tự từ a -> z, A -> Z và 0 -> 9 độ dài từ 4 đến 24 ký tự");
            System.out.print("Vui lòng nhập lại tên đăng nhập: ");
        }

        return check;
    }

    //Check Tên. Nếu tên đúng trả về giá trị là true;
    public boolean checkName(String name) {
        boolean check = false;

        //kiểm tra họ tên, họ tên chỉ chứa các ký tự từ a -> z và A -> Z và độ dài từ 5 -> 40 ký tự
        if (name.matches("[a-z A-Z]{5,40}")) {
            return true;
        } else {
            check = false;
            
        }

        return check;
    }

    public boolean checkFormatPass(String pass) {
        boolean check = false;

        char[] password = pass.toCharArray();
        if(password.length < 8){
            check = true;
        }
        for(char c : password){
            if(c == ' '){
                check = true;
            }
        }
        return check;
    }

  
    public boolean checkEmail(String email) {
        boolean check = false;

        if (email.matches("[a-zA-Z0-9]{6,72}@[a-zA-Z]+\\.[a-zA-Z]{2,4}")) {
            return true;
        } else {
            check = false;
          
        }

        return check;
    }

    public boolean checkPhone(String phone) {
        boolean check = false;

        if (phone.matches("0[1-9][0-9]{8,9}")) {
            return true;
        } else {
            check = false;
            
        }

        return check;
    }
    
    public boolean checkNumber(String str) {
        boolean check = false;

        if (str.matches("[0-9]+")) {
            return true;
        } else {
            check = false;

        }

        return check;
    }
    public boolean check12Number(String str) {
        boolean check = false;

        if (str.matches("[0-9]{12}")) {
            return true;
        } else {
            check = false;
        }

        return check;
    }

    // check giới tính
    public boolean checkGender(String gender) {
        boolean check = false;

        if (gender.equalsIgnoreCase("nam") || gender.equalsIgnoreCase("nu")) {
            check = true;
        } else {
            check = false;
            
        }

        return check;
    }

    //Check lựa chọn
    public boolean checkChoice(String choice) {
        boolean check = false;

        if (choice.equalsIgnoreCase("n") || choice.equalsIgnoreCase("y")) {
            check = true;
        } else {
            check = false;
            
        }

        return check;
    }
}