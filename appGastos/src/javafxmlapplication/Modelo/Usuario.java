/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxmlapplication.Modelo;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author coozy
 */

public class Usuario {
    public String nombre, nickname,password, correo;
    public Image fotoPerfil;
    
    public static ObservableList<Usuario> usuarios;
    public static ObservableList<Usuario> inicioSesion;
    public Usuario(String nombre, String nickname, String password, String correo, Image fotoPerfil) {
        this.nombre = nombre;
        this.nickname = nickname;
        this.password = password;
        this.correo = correo;
        this.fotoPerfil = fotoPerfil;
    }
    public static boolean Userexists(String nickname, String password){
    return inicioSesion.contains(new Usuario(nickname,password))? true : false;
    }
    
    public static void addUser(String nombre,String nickname, String password, String correo, Image fotoPerfil){
    usuarios.add(new Usuario(nombre,nickname,password,correo,fotoPerfil));
    inicioSesion.add(new Usuario(nickname,password));
    }
    
    public Usuario(String nickname, String password){
        this.nickname = nickname;
        this.password = password;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Image getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(Image fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }


}
