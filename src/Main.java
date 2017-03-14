import org.basex.api.client.ClientQuery;
import org.basex.api.client.ClientSession;
import org.basex.core.cmd.CreateDB;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by x3727349s on 13/03/17.
 */
public class Main {

    public static void main(String[] args) {

        String rutaFacktbook = "/home/x3727349s/Baixades/Factbook.xml";
        String rutaMondial = "/home/x3727349s/Baixades/Mondial.xml";
        String bbdd = "tahabakk";
        String username = "admin";
        String pass = "admin";
        String consulta = "";
        String ip ="localhost";

        Scanner sc = new Scanner (System.in);

        int numOpcion = 0;
        do {

            System.out.println("1.Quins països hi ha en el fitxer «factbook.xml»? ");
            System.out.println("2.Quants països hi ha?");
            System.out.println("3.Quina és la informació sobre Alemanya ?");
            System.out.println("4.Quanta gent viu a Uganda segons aquest fitxer");
            System.out.println("5.Quines són les ciutats de Perú que recull aquest fitxer?");
            System.out.println("6.Quanta gent viu a Shanghai?");
            System.out.println("7.Quin és el codi de matricula de cotxe de Xipre?");

            numOpcion = sc.nextInt();

            switch (numOpcion) {
                case 1:
                    consulta = "//country";//pasamos la consulta a realizar
                    consulta(username, pass, rutaFacktbook, bbdd, consulta, ip);//llamamos el metodo pasandole los datos
                    break; // optional

                case 2:
                    consulta = "count(//country)";
                    consulta(username, pass, rutaMondial, bbdd, consulta, ip);
                    break; // optional

                case 3:
                    consulta = "factbook/record[country=\"Germany\"]/introduction/background";
                    consulta(username, pass, rutaMondial, bbdd, consulta, ip);
                    break; // optional

                case 4:
                    consulta = "factbook/record[country=\"Uganda\"]/people/population";
                    consulta(username, pass, rutaMondial, bbdd, consulta, ip);
                    break; // optional

                case 5:
                    consulta = "mondial/country[name=\"Peru\"]/province/city/name";
                    consulta(username, pass, rutaMondial, bbdd, consulta, ip);
                    break; // optional

                case 6:
                    consulta = "mondial/country[name=\"China\"]/province/city[name=\"Shanghai\"]/population";
                    consulta(username, pass, rutaMondial, bbdd, consulta, ip);
                    break; // optional

                case 7:
                    consulta = "mondial/country[name=\"Cyprus\"]/@car_code";
                    consulta(username, pass, rutaMondial, bbdd, consulta, ip);
                    break; // optional

                default: // Optional
                    // Statements
            }

        }while (numOpcion!=0);





    }



    public static void consulta(String username, String pass, String ruta, String bbdd, String consulta, String ip){

        try (ClientSession session = new ClientSession(ip, 1984, username, pass)) {//IP, PUERTO, USERNAME, PASSWORD
            session.execute(new CreateDB(bbdd, ruta));//BD que se creara, y ruta del fitxero
            try (ClientQuery query = session.query(consulta)) {//consulta
                System.out.println(query.execute());//imprimir resultado
            }
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*public void consulta(String username, String pass, String ruta){

        try (ClientSession session = new ClientSession("localhost", 1984, "admin", "admin")) {
            session.execute(new CreateDB("TahaBakk", "/home/x3727349s/Baixades/Factbook.xml"));//BD que se creara, y ruta del fitxero
            try (ClientQuery query = session.query("//country")) {//consulta
                System.out.println(query.execute());//imprimir resultado
            }
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/




}
