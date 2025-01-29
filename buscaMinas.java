import java.util.Scanner;
import java.util.Random;
public class buscaMinas{
	
	private static boolean finDeJuego=false;//Variable golobal Intentar no utilizar Nunca.
	private static int contadorDeDescubiertos=0;
	
	public static char[][] crearTableroMinas(int tamanoX,int tamanoY,int cantidadMinas){
		Random rnd=new Random();
		int x,y;
		char[][]tablaMinas=new char[tamanoX][tamanoY];
		//Seteo todo a zeros
		for(int i=0;i<tablaMinas.length;i++)
			for(int j=0;j<tablaMinas[0].length;j++)
				tablaMinas[i][j]='0';
				
				
		//Introducir minas y cambiar numeros
		for(int i=0;i<cantidadMinas;i++){	
			do{//Comprobar que no hay mina en el espacio random
				x=rnd.nextInt(tamanoX);
				y=rnd.nextInt(tamanoY);
			}while(tablaMinas[x][y]=='m');
			tablaMinas[x][y]='m';
			
			
			//Sumo un numero a los de alrededor
			for(int j=-1;j<=1;j++)
				for(int k=-1;k<=1;k++)
					if(x+j>=0 && x+j<=(tamanoX-1) && y+k>=0 && y+k<=(tamanoY-1) && tablaMinas[x+j][y+k]!='m')
						tablaMinas[x+j][y+k]++;
		}
		
		return tablaMinas;
	}
	
	
	
	
	public static void printeoFinal(char[][]tablero){
		// Printeo los numeros para facilitar la ubicacion de la columna a escojer
			System.out.print("      ");
			System.out.print("  ");
		for(int i=0;i<tablero[0].length;i++){
			System.out.print(i);
			if(i<10)
				System.out.print("   ");
			else
				System.out.print("  ");
		}
			System.out.println();
			System.out.println();
			
			
			
		// Printeo del tablero	
		for(int i=0;i<tablero.length;i++){
			System.out.print("       ");//Encuadrar la linea de encima de los numeros
			for(int l=0;l<tablero[0].length;l++)
				System.out.print("____");//Linia de encima de los numeros
			System.out.println();
			System.out.print(i);//Printeo el numero para facilitar la ubicacion de la fila a escojer
			System.out.print("     ");// Espacios para cuadrar el printeo de los numeros
			System.out.print("| ");//Linea separacion inicial
			for(int j=0;j<tablero[0].length;j++){
				System.out.print(tablero[i][j]);//Printeo del numero
				System.out.print(" | ");//Linea separacion de numeros
			}
			System.out.println();
		}
	}
	
	
	
	public static void printeoNormal(char[][]tablero,boolean[][]descubierto){
		// Printeo los numeros para facilitar la ubicacion de la columna a escojer
			System.out.print("      ");
			System.out.print("  ");
		for(int i=0;i<tablero[0].length;i++){
			System.out.print(i);
			System.out.print("   ");
		}
			System.out.println();
			System.out.println();
			
			
			
		// Printeo del tablero	
		for(int i=0;i<tablero.length;i++){
			System.out.print("       ");//Encuadrar la linea de encima de los numeros
			for(int l=0;l<tablero[0].length;l++)
				System.out.print("____");//Linia de encima de los numeros
			System.out.println();
			System.out.print(i);//Printeo el numero para facilitar la ubicacion de la fila a escojer
			System.out.print("     ");// Espacios para cuadrar el printeo de los numeros
			System.out.print("| ");//Linea separacion inicial
			for(int j=0;j<tablero[i].length;j++){
				if(descubierto[i][j]==true)
					System.out.print(tablero[i][j]);//Printeo del numero
				else
					System.out.print(" ");
				System.out.print(" | ");//Linea separacion de numeros
			}
			System.out.println();
		}
	}
	
	public static boolean[][] crearTableroMostrado(int tamanoX,int tamanoY){
		
	boolean[][]	mostrado=new boolean[tamanoX][tamanoY];
	return mostrado;	
	
	}
	
	public static void descubrirSegunNumero(char[][]tablero,boolean[][]mostrado,int posX,int posY){
	
		if(tablero[posX][posY]!='m'){
			if(tablero[posX][posY]=='0'){
				contadorDeDescubiertos++;
				mostrado[posX][posY]=true;
				for(int i=-1;i<=1;i++)
					for(int j=-1;j<=1;j++)
						if(posX+i<tablero.length && posX+i>=0 && posY+j<tablero[0].length && posY+j>=0 && mostrado[posX+i][posY+j]==false)
							descubrirSegunNumero(tablero,mostrado,posX+i,posY+j);
			}
			else
				contadorDeDescubiertos++;
				mostrado[posX][posY]=true;
		}
		else
			finDeJuego=true;
		
	
	
	}
	public static void main(String[]args){
		Scanner sc=new Scanner(System.in);
		int fila;
		int columna;
		int tamanoX=10;
		int tamanoY=1;
		int cantidad=5;
		char [][]tablero;
		boolean[][]descubierto;
		//Preparar tablero por tablero
		System.out.print("Cuantas columnas quieres poner: ");
		tamanoX=sc.nextInt();
		System.out.print("Cuantas filas quieres poner: ");
		tamanoY=sc.nextInt();
		System.out.print("Cuantas minas quieres poner: ");
		cantidad=sc.nextInt();
		
		tablero=crearTableroMinas(tamanoX,tamanoY,cantidad);
		descubierto=crearTableroMostrado(tamanoX,tamanoY);
		
		while(finDeJuego==false && contadorDeDescubiertos<(tamanoX*tamanoY-cantidad)){
			printeoNormal(tablero,descubierto);
			System.out.println("Que quieres Descubrir");
			do{
				System.out.print("Fila: ");
				fila=sc.nextInt();
			}while(fila>=tamanoY || fila<0);
			do{
				System.out.print("Columna: ");
				columna=sc.nextInt();
			}while(columna>=tamanoX || columna<0);
			
			descubrirSegunNumero(tablero,descubierto,fila,columna);
		}
	
		//FIN DE JUEGO
		if(contadorDeDescubiertos==(tamanoX*tamanoY-cantidad)){
			System.out.println("Felicidades has ganado!!!!. \n Este era el tablero:");
			printeoFinal(tablero);
		}
		else{
			System.out.println("Has perdido :(\n Este era tu tablero:");
			printeoFinal(tablero);
		}
	}	
}
