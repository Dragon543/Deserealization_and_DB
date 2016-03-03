package MyPack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.UUID;

/**
 * Created by taras on 27.02.2016.
 */
public class MainClass {
    public static void main(String[] args) throws Exception {
        int a=5;
        float paymentDate;
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("5.dat"))){
                Payments pay = (Payments)in.readObject();
                System.out.print(pay+"\n");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("6.dat"))){
                Payments pay = (Payments)in.readObject();
                System.out.print(pay+"\n");}
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("7.dat"))){
                Payments pay = (Payments)in.readObject();
                System.out.print(pay+"\n");}
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("8.dat"))){
                Payments pay = (Payments)in.readObject();
                System.out.print(pay+"\n");}
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("9.dat"))){
                Payments pay = (Payments)in.readObject();
                System.out.print(pay+"\n");}
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        MySQLAccess dao = new MySQLAccess();
        dao.runQueries();
        }
    }


