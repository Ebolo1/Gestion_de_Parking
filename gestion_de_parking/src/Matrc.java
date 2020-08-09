public class Matrc implements Infmat {

    public char mat[][];
    Matrc(){
        mat=new char[32][32];
        for(int j=0;j<32;j++)
            for(int k=0;k<32;k++){
                mat[j][k]=' ';
            }

        for(int j=0;j<32;j++)
            for(int k=0;k<32;k++){
                if(j==0||(j==31&&(k!=31&&k!=30&&k!=29)))
                    mat[j][k]='-';
                if(k==0||k==31)
                    mat[j][k]='|';
            }

        for(int j=2;j<31;j+=4)
            for(int k=1;k<29;k+=2){
                mat[j][k]=' ';
            }

        for(int j=1;j<31;j+=2)
            for(int k=1;k<29;k+=1){
                mat[j][k]='-';
            }

        for(int j=2;j<31;j+=4)
            for(int k=2;k<29;k+=2){
                mat[j][k]='|';}
    }

    public Point allocate(char type){        //allocating location
        int m=1;
        Point locate=new Point();
        locate.x=-1;
        locate.y=-1;
        for(int j=2;j<31;j+=4){
            for(int k=1;k<29;k+=2){
                if(mat[j][k]==' '&&m==1){
                    mat[j][k]=type;m=0;

                    locate.x=((j-2)/4)+1;
                    locate.y=((k-1)/2)+1;
                    return locate;
                }
            }
        }
        return locate;
    }


    public boolean remove(Point locate)  //removing vehicle
    {
        if(mat[locate.x][locate.y]==' '){
            return false;
        }

        mat[locate.x][locate.y]=' ';
        return true;
    }

    public void display()
    {
        for(int j=0;j<32;j++){System.out.print("\t\t\t\t\t\t");
            for(int k=0;k<32;k++){
                System.out.print(mat[j][k]+" ");
            }
            System.out.println("");
        }
    }
}

