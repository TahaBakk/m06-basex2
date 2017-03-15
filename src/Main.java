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

        String rutaFacktbook = "/home/x3727349s/Baixades/Factbook.xml";//cambiar por la ruta absoluta
        String rutaMondial = "/home/x3727349s/Baixades/mondial.xml";//cambiar por la ruta absoluta
        String bbdd = "tahabakk";//Nombre de la bbdd
        String username = "admin";//usuario de baseX
        String pass = "admin";//Contrasenya de baseX
        String consulta = "";//Consulta se añade en el switch dependiendo de la opcion seleccionada
        String ip ="localhost";//ip del server/baseX

        Scanner sc = new Scanner (System.in);

        int numOpcion = 0;
        do {
            System.out.println("");
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("1.Quins països hi ha en el fitxer «factbook.xml»? ");
            System.out.println("2.Quants països hi ha?");
            System.out.println("3.Quina és la informació sobre Alemanya ?");
            System.out.println("4.Quanta gent viu a Uganda segons aquest fitxer");
            System.out.println("5.Quines són les ciutats de Perú que recull aquest fitxer?");
            System.out.println("6.Quanta gent viu a Shanghai?");
            System.out.println("7.Quin és el codi de matricula de cotxe de Xipre?");
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("");

            numOpcion = sc.nextInt();

            switch (numOpcion) {
                case 1:
                    consulta = "//country";//pasamos la consulta a realizar
                    System.out.println("Els països que conté son els següents");
                    consulta(username, pass, rutaFacktbook, bbdd, consulta, ip);//llamamos el metodo pasandole los datos
                    break; // optional

                case 2:
                    consulta = "count(//country)";
                    System.out.println("La quantitat de països que hi ha son: ");
                    consulta(username, pass, rutaMondial, bbdd, consulta, ip);
                    break; // optional

                case 3:
                    consulta = "//record[country=\"Germany\"]/introduction/background";
                    System.out.println("La indormació sobre alemanya és:");
                    consulta(username, pass, rutaFacktbook, bbdd, consulta, ip);
                    break; // optional

                case 4:
                    consulta = "//record[country=\"Uganda\"]/people/population";
                    System.out.println("La quantitat de població que hi ha en Uganda és: ");
                    consulta(username, pass, rutaFacktbook, bbdd, consulta, ip);
                    break; // optional

                case 5:
                    consulta = "//country[name=\"Peru\"]/province/city/name";
                    System.out.println("Les ciutats de peru son: ");
                    consulta(username, pass, rutaMondial, bbdd, consulta, ip);
                    break; // optional

                case 6:
                    consulta = "//country[name=\"China\"]/province/city[name=\"Shanghai\"]/population";
                    System.out.println("La quantitat de població és de: ");
                    consulta(username, pass, rutaMondial, bbdd, consulta, ip);
                    break; // optional

                case 7:
                    consulta = "//country[name=\"Cyprus\"]/@car_code";
                    System.out.println("La matricula es: ");
                    consulta(username, pass, rutaMondial, bbdd, consulta, ip);
                    break; // optional

                default: // Optional
                    // Statements
            }

        }while (numOpcion!=0);//si es 0 sale del blucle y finaliza


        System.out.println("Programa finalizado ^^");


    }


    //Funcio que ejecuta la consulta al fitxero
    public static void consulta(String username, String pass, String ruta, String bbdd, String consulta, String ip){

        try (ClientSession session = new ClientSession(ip, 1984, username, pass)) {//IP, PUERTO, USERNAME, PASSWORD
            session.execute(new CreateDB(bbdd, ruta));//BBDD que se creara, y ruta del fitxero
            try (ClientQuery query = session.query(consulta)) {//consulta
                System.out.println(query.execute());//imprimir resultado
            }
            session.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
