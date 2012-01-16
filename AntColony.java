class Ant extends Thread
{
       private int id_Num;
       private boolean inside;
       private AntHill mound;

       public Ant(int id,AntHill hill)
       { 
             this.id_Num=id;
             this.inside=false;
             this.mound=hill;
       }

       public int takeId()
       {
             return id_Num;
       }

       public boolean isInside()
       {
             return inside;
       }

       public void goInside()
       {
             inside=true;
       }

       public void tryEnter()
       {  
             if(mound.aquire())
             {
                goInside();
                System.out.println("Ant "+takeId()+"enter to hill");
           
                try{
                     sleep(500-id_Num*10);
                   }
                    catch(InterruptedException e)
                    {
                       System.out.println("exception arised");
                    }

                mound.release();
      
             }
        }  

       public void run()
       {
              while(!isInside())
              {
                  tryEnter();
              }
       }

}


class AntHill
{
      private boolean pathAvailable;

      public AntHill()
      {
           this.pathAvailable=true;
      }

      public  synchronized boolean aquire()
      {
           if(pathAvailable)
           {
               pathAvailable=false;
               return true;
           }
          
           else
               return false;
     
      }

      public void release()
      {
           if(!pathAvailable)
           {
                pathAvailable=true;
           }
      
      }

}



public class AntColony
{
      private static final int COLONY_SIZE=20;
      private Ant[] colony;
      private AntHill mound;
 
      public AntColony()
      {
            this.colony=new Ant[COLONY_SIZE];
            this.mound=new AntHill();
      }

      public void antBorn()
      {
            for(int i=0;i<COLONY_SIZE;i++)
               {
                    colony[i]=new Ant(i,mound);
                    colony[i].start();
               }

      }


      public static void main(String[] args)
      {
            AntColony yard=new AntColony();
            yard.antBorn();
      }


}