package vut.data;

import java.sql.SQLException;
import vut.gui.*;

/**
 *
 * @author B Dywili 217004547 
 *         
 */
public class AdelaneFashionsScreenLoader {

    /**
     * @param args the command line arguments
     * @throws vut.data.DataStorageException
     */
    public static void main(String[] args) throws DataStorageException , SQLException
    {
        AdelaneFashionsFrame frm2 = new AdelaneFashionsFrame();
        frm2.setTitle("Welcome To Adelane Fashions Stores");
        frm2.setVisible(true);
       
        AdelaneFashionsDA.initConnection();
          
        
        
    }
    
}
