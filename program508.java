import java.util.*;
import java.io.*;

class program508s {

    public static void main(String Arg[]) throws Exception {

        Scanner sobj = new Scanner(System.in);

        System.out.println("--------Marvellous Packer Unpacker CUI Module------");

        System.out.println("Enter File name tha you to open:");
        String File_Name = sobj.nextLine();

        File fobj = new File(File_Name);

        if (fobj.exists()) {
            FileOutputStream foobj = new FileOutputStream(fobj);

            String str = "Marvellous";

            fobj.write(str.getBytes()); // Java 7+ only

        } else {
            System.out.println("There is no such file");
        }
    }
}