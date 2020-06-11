package com.example.tallematerialdesign;

import java.util.ArrayList;

public class Carro {
    private String marca;
    private String modelo;
    private String ano;
    private String cilindrada;
    private String placa;
    private int foto;
    private String id;

    Carro(String marca, String modelo, String ano, String cilindrada, String Placa,int foto){
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cilindrada = cilindrada;
        this.placa = placa;
        this.foto = foto;
    }

    Carro(String marca, String modelo, String ano, String cilindrada, String Placa,int foto, String id){
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cilindrada = cilindrada;
        this.placa = placa;
        this.foto = foto;
    }

    public Carro(){}

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(String cilindrada) {
        this.cilindrada = cilindrada;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void guardar(){
        Datos.guardar(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void eliminar(){
        Datos.eliminar(this);
    }
}
