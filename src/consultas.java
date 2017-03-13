import org.basex.api.client.ClientQuery;
import org.basex.api.client.ClientSession;
import org.basex.core.cmd.CreateDB;

import java.io.IOException;

/**
 * Created by x3727349s on 13/03/17.
 */
public class consultas {

public void consultaCountry(){

    try (ClientSession session = new ClientSession("localhost", 1984, "admin", "admin")) {
            session.execute(new CreateDB("TahaBakk", "/home/x3727349s/Baixades/Factbook.xml"));//BD que se creara, y ruta del fitxero
            try (ClientQuery query = session.query("//country")) {//consulta
                System.out.println(query.execute());//imprimir resultado
            }
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
