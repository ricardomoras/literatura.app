package com.ricardo.literatura.app.main;

import java.util.Scanner;

import com.ricardo.literatura.app.models.Data;
import com.ricardo.literatura.app.models.DataBook;
import com.ricardo.literatura.app.services.ConvertData;
import com.ricardo.literatura.app.services.GetAPI;



public class Principal {

	Scanner keyBoard = new Scanner(System.in);
    private GetAPI getApi = new GetAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvertData convertData = new ConvertData();
    
    public void showMenu() {
        var opcion = -1;
        while (opcion != 0) {

            System.out.println("Selecciona una opcion");
            try {
                opcion = keyBoard.nextInt();
                keyBoard.nextLine();
            } catch (Exception e){
                System.out.println("Opción inválida");
                break;
            }
            findBookWeb();
        }
    }

    private void findBookWeb(){
        Data data = getDataBook();
        System.out.println("data = " + data);
    }
    private Data getDataBook (){
        System.out.println("Escribe el nombre del libro");
        var nameBook = keyBoard.nextLine();
        var json = getApi.getData(URL_BASE + nameBook.replace(" ", "%20"));
        System.out.println(json);
        Data data = convertData.getData(json, Data.class);
        return data;
    }
}
