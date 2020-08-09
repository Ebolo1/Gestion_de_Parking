import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)throws IOException, InterruptedException  {


        Point locate=new Point();
        Matrc grid=new Matrc();
        //  File f1=new File("matrix.txt");

        Scanner scan=new Scanner(System.in);


        System.out.print("\033[H\033[2J");
        System.out.println("\n\n***********************************************************************************");
        System.out.println("\n\n\n\n\n\n\n\n\t\t\t\t\t\t\tBienvenu a system de gestion de Parking\n\n\n\n\n\n");
        System.out.print("Developer pas :\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
        System.out.println("\n\tEkangwo Hernadez Ebolo\t\t\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("**************************************************************************************");

        System.out.print("\t\t\t\t\t\t\t");System.out.println("Appuyez sur ENTER pour continuer");
        String line=null;
        System.out.print("\t\t\t\t\t\t\t");
        while ((line =scan.nextLine()).length() > 0)
        {
        }
        System.out.println("\033[H\033[2J\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t\t\tChargement des informations de base de données...");


        try{
            Thread.sleep(2000);
        }
        catch(InterruptedException e){
            System.out.println("Interruption détectée!");
        }

        BufferedReader br = null;
        try{
            br=new BufferedReader(new InputStreamReader(new FileInputStream("Matrix.txt")));// base de donnée pour stoker les information enregistre                                            //loading the previous matrix into progran
            for(int i=0; i<32; i++){
                for(int j=0;j<32; j++){
                    grid.mat[i][j]=(char)br.read();
                }
            }
            if(br!=null)
                br.close();
        }
        catch(IOException e)
        {
            System.out.println("\033[H\033[2J\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t\t\t\tBase de données non présente. Un nouveau fichier vide sera créé.");
            File f1=new File("Matrix.txt");
            //f1.createNewFile();
        }


        System.out.print("\033[H\033[2J");
        stop:
        while(true){
            System.out.println("\t\t\t\t\t\t\t\tVoici les choix suivants:\n\t\t\t\t\t\t\t\t1. Entrez un nouveau véhicule\n\t\t\t\t\t\t\t\t2. Sortir d’un véhicule\n\t\t\t\t\t\t\t\t3. Afficher la grille\n\t\t\t\t\t\t\t\t4. Afficher les journaux(log)\n\t\t\t\t\t\t\t\t5. Exit\n\t\t\t\t\t\t\t\tEntrez votre choix: ");


            int choice=0;

            while(true){
                try{
                    System.out.print("\t\t\t\t\t\t\t\t");
                    choice=scan.nextInt();
                    if(choice<=5&&choice>=1)
                        break;
                    else
                        throw new InputMismatchException();
                }
                catch(InputMismatchException e){
                    System.out.print("\n\n\t\t\t\t\t\t\tEntrez le choix valide: \n");
                    scan.next();
                }
            }

            switch(choice){

                case 1:
                    System.out.print("\033[H\033[2J");
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t\tEntrez le type de véhicule...'V' pour la voiture et 'M' pour deux-roues(Moto).");

                    char type=' ';
                    while(true){
                        try{
                            System.out.print("\t\t\t\t\t\t\t");
                            type=scan.next().charAt(0);
                            if(type!='V'&&type!='M')
                                throw new InputMismatchException();
                            else
                                break;
                        }
                        catch(InputMismatchException e){
                            System.out.println("\n\n\t\t\t\t\t\t\tEntrez le choix valide.");
                        }
                    }

                    locate =grid.allocate(type);
                    if(locate.x!=-1){
                        System.out.print("Entrez le nom: ");
                        scan.nextLine();
                        System.out.print("");
                        String name=scan.nextLine();
                        String vehNo;
                        while(true) {
                            System.out.print("Entrez le numéro du véhicule (Exemple : CE 548 MF) : ");
                            System.out.print("");
                            vehNo = scan.nextLine();

                                if (vehNo.charAt(0) >= 65 && vehNo.charAt(0) <= 90 && vehNo.charAt(1) >= 65 && vehNo.charAt(1) <= 90 && vehNo.charAt(2) == 32 && vehNo.charAt(3) >= 48 && vehNo.charAt(3) <= 57 && vehNo.charAt(4) >= 48 && vehNo.charAt(4) <= 57 && vehNo.charAt(5) >= 48 && vehNo.charAt(5) <= 57 && vehNo.charAt(6) == 32 && vehNo.charAt(7) >= 65 && vehNo.charAt(7) <= 90 && vehNo.charAt(8) >= 65 && vehNo.charAt(8) <= 90)
                                    break;
                                else
                                    System.out.println("\n\n\t\t\t\tVeuillez saisir une plaque d’immatriculation au standard Camerounais valide comme indiqué.\n\n");

                        }
                        String cellNo;
                        while(true){
                            System.out.print("Entrez le numéro de téléphone cellulaire: ");
                            System.out.print("");
                            cellNo=scan.next();
                            if(cellNo.length()!=9)
                                System.out.println("\n\n\t\t\t\tEntrez une numero de contact valide(Exemple: 675894518) .\n\n");
                            else{
                                System.out.println();
                                break;
                            }
                        }


                        File file;
                        try{
                            file=new File("log.txt");//
                            if(!file.exists()){
                                file.createNewFile();
                            }
                            PrintWriter pw=new PrintWriter(new FileWriter(file, true));
                            pw.println("Entrée:");
                            pw.println("Nom: "+name);
                            if(type=='V')
                                pw.println("Type de véhicule: Voiture");
                            else
                                pw.println("Type de véhicule: deux-roues(Moto)");
                            pw.println("Numéro du véhicule: "+vehNo);
                            pw.println("Numero de telephone: "+ cellNo);
                            pw.println("Emplacement du stationnement(Coordonnées): "+locate.x+" ,"+locate.y);

                            int day, month, year;
                            int second, minute, hour;
                            GregorianCalendar date = new GregorianCalendar();                                              //created object for calender

                            day = date.get(Calendar.DAY_OF_MONTH);
                            month = date.get(Calendar.MONTH);
                            year = date.get(Calendar.YEAR);

                            second = date.get(Calendar.SECOND);
                            minute = date.get(Calendar.MINUTE);
                            hour = date.get(Calendar.HOUR);
                            pw.println("Date: "+day+"/"+(month+1)+"/"+year);                                                    //printing data into file
                            pw.println("Heur: "+hour+":"+minute+":"+second);
                            pw.println();
                            pw.println();
                            pw.close();

                        }
                        catch(IOException e){
                            System.out.println("\t\t\t\t\t\t\tProblème dans la création de journaux, entrez manuellement les journaux.");
                            System.out.print("\033[H\033[2J");
                        }

                        System.out.println("\n\n\n\n\t\t\t\t\t\t\tVotre emplacement de stationnement est ligne "+locate.x+" et colonne "+locate.y+" .\n\n");
                        try{
                            Thread.sleep(3000);
                        }
                        catch(InterruptedException e){
                            System.out.println("Interruption.");
                        }
                        System.out.print("\033[H\033[2J");

                    }else
                        System.out.println("\033[H\033[2J\n\n\n\n\t\t\t\t\t\t\tDésolé, toutes les places de parking sont pleines.\n\n\n\n\n\n\n");

                    break;

                case 2:
                    System.out.println("\n\n\n\n");
                    grid.display();
                    System.out.println("\t\t\t\t\t\t\tEntrez les coordonnées de sortir d'une véhicule:(Exemple : x y)");
                    System.out.print("\t\t\t\t\t\t\t");
                    locate.x=scan.nextInt();
                    locate.x=4*(locate.x-1)+2;
                    locate.y=scan.nextInt();
                    locate.y=2*(locate.y-1)+1;
                    boolean check=false;
                    check=grid.remove(locate);

                    if(check==true){
                        System.out.print("Entrez le numéro du véhicule: ");
                        System.out.print("");
                        scan.nextLine();
                        System.out.print("");
                        String exiting=scan.nextLine();
                        try{
                            File file=new File("log.txt");
                            if(!file.exists())
                                file.createNewFile();

                            PrintWriter pw=new PrintWriter(new FileWriter(file, true));
                            pw.println("Exit:");
                            pw.println("numéro du véhicule: "+exiting);
			 /*DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			 LocalDateTime now=LocalDateTime.now();
			 pw.println("Date & Time: "+dtf.format(now));*/
                            int day, month, year;
                            int second, minute, hour;
                            GregorianCalendar date = new GregorianCalendar();

                            day = date.get(Calendar.DAY_OF_MONTH);
                            month = date.get(Calendar.MONTH);
                            year = date.get(Calendar.YEAR);

                            second = date.get(Calendar.SECOND);
                            minute = date.get(Calendar.MINUTE);
                            hour = date.get(Calendar.HOUR_OF_DAY);
                            pw.println("Date: "+day+"/"+(month+1)+"/"+year);
                            pw.println("Heur: "+hour+":"+minute+":"+second);
                            pw.println();
                            pw.println();
                            pw.close();
                        }
                        catch(IOException e){
                            System.out.println("\t\t\t\t\t\t\tÉchec de l’entrée du journal de sortie, entrez manuellement dans le fichier.");
                        }

                    }
                    else
                        System.out.println("\n\n\t\t\t\t\t\t\tIl n’y a pas de véhicule dans l’intrigue.\n\n");

                    break;

                case 3:
                    grid.display();
                    break;

                case 4:
                    try{
                        File file=new File("log.txt");
                        BufferedReader bri=new BufferedReader(new FileReader(file));
                        String buffer;
                        System.out.println("\033[H\033[2J\n\t\t\t\t\t\t\tAffichage des journaux précédents...");
                        System.out.println();
                        System.out.println();
                        while((buffer=bri.readLine())!=null){
                            System.out.println(buffer);
                        }
                        bri.close();

                    }
                    catch(IOException e){
                        System.out.println("\033[H\033[2J\t\t\t\t\t\t\tErreur d’affichage des journaux sur la console, reportez-vous au fichier manuellement.");
                    }
                    break;

                default:
                    break stop;
            }


            //scan.next();

        }
        BufferedWriter bo = null;
        System.out.println("\033[H\033[2J\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t\t\tEnregistrement des informations de base de données...");
        try{
            Thread.sleep(2000);
        }
        catch(InterruptedException e){
            System.out.println("Interruption détectée!");
        }

        try{
            bo = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Matrix.txt")));
            for(int i=0; i<32;  i++){
                for(int j=0; j<32; j++){
                    bo.write(grid.mat[i][j]);
                }
            }
            if(bo!=null)
                bo.close();
        }
        catch(IOException e){
            System.out.println("IO error.");

        }
        System.out.println("\033[H\033[2J\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t\tMerci d’utiliser notre logiciel et avoir une bonne journée à venir!!\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}

