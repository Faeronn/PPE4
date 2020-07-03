package com.PPE4.gsb.objects;

import java.util.ArrayList;

public class Visite {
    private int _id;
    private String _description;
    private String _date;
    private ArrayList<Frais> _fraisList = new ArrayList<>();

    public Visite(int id, String date, String description, ArrayList<Frais> fraisList) {
        this._id = id;
        this._date = date;
        this._description = description;
        this._fraisList = fraisList;
    }

    public void set_id(int id) {
        this._id = id;
    }


    public int get_id() {
        return _id;
    }

    public String get_date() {
        return _date;
    }

    public  String get_description() {
        return _description;
    }

    public ArrayList<String> get_fraisList() {
        ArrayList<String> out = new ArrayList<>();

        for(Frais frais : _fraisList) {
            out.add(frais.get_all_infos());
        }

        return out;
    }

}
