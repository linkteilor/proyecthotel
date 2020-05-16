
package data;

import entities.client;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class clientData {
    List<client> lis=new CopyOnWriteArrayList<client>();
    int id;
    public clientData(){
        id=0;
    }

    public List<client> List() {
        return lis;
    }

    public void create( client d) {
       id=id+1;
       System.out.println("ID"+id);
       d.setId(id);
       lis.add(d);
    }


    public void delete( int id) {
        for(client d:lis){
            if(id==d.getId()){
                lis.remove(d);
                System.out.println("eliminado"+d.getnombre());
            }
        }
    }
    
}
