import java.util.Scanner;
import java.util.Random;
public class Siete_y_Medio {
	
	public static void main (String[] args) {
		Scanner sc=new Scanner(System.in);
		Random rnd=new Random();
		int []Cartas={1,2,3,4,5,6,7,10,11,12};
		int []CCS=new int[10]; //Cantidad de cartas salidas
		int Jugadores;
		int [][]Mesa;
		double [] Puntuaciones;
		boolean []Situacion;
		int Carta;
		int opcion;
		boolean Salida;
		int lugar=1;
		double Suma;
		boolean Ronda1=true;
		boolean []Winner;
		double CantidadDelGan;
		boolean continuar;
		int cantidadDeWinners;
		char hayBots='f';
		int [][]mesaBots=new int[1][1];
		int nBots;
		int []dificultadBots;
		char dificultadElegida;
		int cartaMaximaBots;
		double []sumaBots=new double[1];
//**************************Inicialización******************************		
		System.out.print("Cuantas personas van a jugar?(1-10): ");
		do{
			Jugadores=sc.nextInt();
			if(Jugadores>10 || Jugadores<1)
				System.out.println("No pueden jugar "+Jugadores+" Jugadores,solo puede haber de (1-10) jugadores, intoduce un nuevo valor: ");
	//********************** bots***************************************
		}while(Jugadores>10 || Jugadores<1);
		System.out.print("Quieres añadir bots?;(y-yes,n-no)");
		if(Jugadores<10){
			do{
				sc.nextLine();
				hayBots=sc.nextLine().charAt(0);
				if(hayBots!='y' && hayBots!='n')
					System.out.print("Opcion no valida introduce Y para yes o N para no: ");
			}while(hayBots!='y' && hayBots!='n');
			if(hayBots=='y'){
				System.out.print("Cuantos Bots quieres añadir? :");
				do{
					nBots=sc.nextInt();
					sc.nextLine();
					if(Jugadores+nBots>10)
						System.out.print("Demasiados jugadores activos, reduzca el nº de bots: ");
					if(nBots<1)
						System.out.println("Opcion no valda introduce una nueva: ");
				}while(Jugadores+nBots>10 || nBots<1);
				mesaBots=new int[nBots][8];
				sumaBots=new double[nBots];
				dificultadBots=new int[nBots];
				
				
		//REPARTO INICIAL BOTS	
		
			for(int i=0;i<mesaBots.length;i++){
				continuar=false;
				while(continuar!=true){
					Carta=rnd.nextInt(10);
					if(CCS[Carta]<4){
						CCS[Carta]++;
						mesaBots[i][0]=Cartas[Carta];
						continuar=true;
					}
				}
			}
		
		//EJECUCION DE BOTS
				//***************Suma de puntos*************************
		
		
			
				for(int i=0;i<dificultadBots.length;i++ ){
					
					
						cartaMaximaBots=Cartas[rnd.nextInt(7)];
						//*********************SUMA*********************************
						Suma=0;
						
						if(mesaBots[i][0]==12 || mesaBots[i][0]==11 || mesaBots[i][0]==10)
							Suma=Suma+0.5;
						else
							Suma=Suma+mesaBots[i][0];
						
						//***********************Juego para los bots*****************
						lugar=1;
						while(Suma<cartaMaximaBots){
							mesaBots[i][lugar]=Cartas[rnd.nextInt(10)];
							if(mesaBots[i][lugar]==12 || mesaBots[i][lugar]==11 || mesaBots[i][lugar]==10)
								Suma=Suma+0.5;
							else
								Suma=Suma+mesaBots[i][lugar];
							lugar++;
						}
					if(Suma<7.5)	
						sumaBots[i]=Suma;
					else
						sumaBots[i]=-1;
				}
				
		
			}
			
		}	
		Mesa=new int[Jugadores][8];
		Situacion=new boolean[Jugadores];
		Puntuaciones=new double[Jugadores];
		Winner=new boolean[Jugadores];
//*********************Reparto inical***********************************

		for(int i=0;i<Mesa.length;i++){
			continuar=false;
			while(continuar!=true){
				Carta=rnd.nextInt(10);
				if(CCS[Carta]<4){
					CCS[Carta]++;
					Mesa[i][0]=Cartas[Carta];
					continuar=true;
				}
			}
		}
		System.out.println("\n\n\n\n\n");
		//*****************Condicion de salida **********************************
		
		
		Salida=true;
		for(int i=0;i<Jugadores;i++){
			if(Situacion[i]==false)
				Salida=false;
		}

//*************************Ejecución************************************
		for(int i=0;i<Jugadores;i++){
			lugar=1;
			
		//***********************Pausita****************************
			if(Ronda1==true){
				
				System.out.print("Pulsa Enter para comenzar: ");
				sc.nextLine();
				Ronda1=false;
			}
			else{
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				sc.nextLine();
				System.out.print("Introduce Enter para continuar: ");
				sc.nextLine();
			}
		//***********************Inicio de ronda********************
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("Jugador "+(i+1)+" Te toca");

			System.out.println("\n\n");
			
		//**************************Printeao mesa*************	
			System.out.println("Esta es la situacion de la partida: ");
			//****************Compruebo si hay bots**************
			if(hayBots=='y'){
				//******************Printeo mesa de bots****************
				for(int j=0;j<mesaBots.length;j++){
					System.out.print("Bot "+(j+1)+":  ");
					for(int k=0;k<mesaBots[j].length;k++){
						if(k==0)
							System.out.print("h ");
						else
						System.out.print(mesaBots[j][k]+ " ");
					}
					System.out.println();
				}
			}
			//*******************printeo mesa de personas***************
			for(int j=0;j<Mesa.length;j++){
				System.out.print("Jugador"+(j+1)+":  ");
				for(int k=0;k<Mesa[j].length;k++){
					if(k==0 && j!=i)
						System.out.print("h ");
					else
						System.out.print(Mesa[j][k]+ " ");
				}
				System.out.println();
			}
			System.out.println("\n\n\n");
			
			//****************Printea cartas de jugador actual*****************
			System.out.println("Ahora tienes:");
				for(int l=0;l<8;l++)
					System.out.print(Mesa[i][l]+" ");
			System.out.println("\n\n\n");
			
			
			//**************************************************************************************************************************************************
			//******************Pido opcion*************************
			opcion=0;
			while(opcion!=2){
				System.out.println("Opciones:");
				System.out.println("1-Pedir otra carta\n2-Plantarte\n3-Ver como esta la partida");
				System.out.println("Jugador"+(i+1)+" Que quieres hacer?:");
				opcion=sc.nextInt();
				System.out.println("\n\n\n");
				if(opcion==2){//TE plantas
					//***************Suma de puntos*************************
					Suma=0;
					for(int a=0;a<=lugar;a++){
						if(Mesa[i][a]==12 || Mesa[i][a]==11 || Mesa[i][a]==10)
							Suma=Suma+0.5;
						else
							Suma=Suma+Mesa[i][a];
					}
					//*****************Guardar suma*********************************
					Puntuaciones[i]=Suma;
				}
				if(opcion==3){
				//**************************Volver a ver la mesa********************	
				System.out.println("Esta es la situacion de la partida: ");
						//****************Compruebo si hay bots**************
					if(hayBots=='y'){
						//******************Printeo mesa de bots****************
						for(int j=0;j<mesaBots.length;j++){
							System.out.print("Bot "+(j+1)+":  ");
							for(int k=0;k<mesaBots[j].length;k++){
								if(k==0)
									System.out.print("h ");
								else
								System.out.print(mesaBots[j][k]+ " ");
							}
							System.out.println();
						}
					}
				
					//*******************printeo mesa de personas***************
					for(int j=0;j<Mesa.length;j++){
						System.out.print("Jugador"+(j+1)+":  ");
						for(int k=0;k<Mesa[j].length;k++){
							if(k==0 && j!=i)
								System.out.print("h ");
							else
								System.out.print(Mesa[j][k]+ " ");
						}
						System.out.println();
					}
				}
				
				if(opcion==1){//Pedir otra carta
					System.out.println("Aqui tienes otra carta");
					continuar=false;
					while(continuar!=true){
						Carta=rnd.nextInt(10);
						if(CCS[Carta]<4){
							CCS[Carta]++;
							Mesa[i][lugar]=Cartas[Carta];
							continuar=true;
							lugar++;
						}	
					}
				//***************Suma de puntos*************************
					Suma=0;
					for(int a=0;a<=lugar;a++){
						if(Mesa[i][a]==12 || Mesa[i][a]==11 || Mesa[i][a]==10)
							Suma=Suma+0.5;
						else
							Suma=Suma+Mesa[i][a];
					}
				//****************Printea cartas actual**********************
					System.out.println("Ahora tienes:");
						for(int l=0;l<8;l++)
							System.out.print(Mesa[i][l]+" ");
					System.out.println("\n\n\n");
							
				//***************Te pasas*******************************
					if(Suma>7.5){
						opcion=2;
						System.out.println("Te has pasado");
						Situacion[i]=true;
						Puntuaciones[i]=-1;
					}	
				}
			}
		}
//***********************************Final Del Juego****************************************
		//*************Puntuación Maximo****************
		CantidadDelGan=Puntuaciones[0];
		for(int p=1;p<Puntuaciones.length;p++)
			if(Puntuaciones[p]>Puntuaciones[p-1])
				CantidadDelGan=Puntuaciones[p];
		if(hayBots=='y'){
			for(int i=0;i<sumaBots.length;i++)
				if(CantidadDelGan<sumaBots[i])
					CantidadDelGan=sumaBots[i];
		}
		//***************Cuantos Ganadroes Hay?*************
			cantidadDeWinners=0;
			if(CantidadDelGan!=-1){
				for(int i=0;i<Puntuaciones.length;i++)
					if(Puntuaciones[i]==CantidadDelGan)
						cantidadDeWinners++;
				if(hayBots=='y')
					for(int i=0;i<sumaBots.length;i++)
						if(sumaBots[i]==CantidadDelGan)
							cantidadDeWinners++;
			}
		//********************Mas de 1 gandador Empates**************************
			if(cantidadDeWinners>1){
				System.out.println("Ha habido un empate entre:");
				for(int i=0;i<Puntuaciones.length;i++)
					if(Puntuaciones[i]==CantidadDelGan)
						System.out.println("El jugador "+ (i+1));
				if(hayBots=='y')
					for(int i=0;i<sumaBots.length;i++)
						if(sumaBots[i]==CantidadDelGan)
							System.out.println("El Bot "+ (i+1));
					
				System.out.println("Con una puntuacion de: "+CantidadDelGan);			
			}
			else
			//************************Un ganador************************
				if(cantidadDeWinners==1){
					System.out.println("El ganador es: ");
					for(int l=0;l<Puntuaciones.length;l++)
						if(Puntuaciones[l]==CantidadDelGan)
							System.out.println("El jugador "+(l+1));
					
					if(hayBots=='y')
						for(int i=0;i<sumaBots.length;i++)
							if(sumaBots[i]==CantidadDelGan)
								System.out.println("El Bot "+ (i+1));
					System.out.println("Con una puntuacion de: "+CantidadDelGan);
				}
				
				else
			//************************Se pasan todos********************
					System.out.println("Habeis perdido todos que os habeis pasado!");
				
		
	}
}

