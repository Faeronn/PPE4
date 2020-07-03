package com.PPE4.gsb.objects;

public class Frais {
    private int _id;
    private String _info;
    private double _montant;
    private int _quantite;

    public Frais(String info, double montant, int quantite) {
        this._info = info;
        this._montant = montant;
        this._quantite = quantite;
    }

    public void set_id(int id) {
        this._id = id;
    }

    public String get_info() {
        return _info;
    }

    public double get_montant() {
        return _montant;
    }

    public int get_quantite() {
        return _quantite;
    }

    public String get_all_infos() {
        return _info + "|" + _montant + " | " + _quantite;
    }
}
