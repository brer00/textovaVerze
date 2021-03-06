package com.github.brer00.adventura.logika;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * Trida Batoh reprezentuje uloziste predmetu tridy @Predmet 
 * Predmety lze do batohu pridavat a lze je z nej odlozit
 * Batoh ma kapacitu, tj. maximalni pocet predmetu, ktere pojme
 * 
 * @author     Radim Břenek
 * @version    16.5.2017
 */
public class Batoh extends Observable {

    private Map<String, Predmet> predmety;
    private int kapacita;

    
	public Batoh(int kapacita) 
	{
		predmety = new HashMap<String, Predmet>();
		this.kapacita = kapacita;
	}

	/**
	 * @return vraci true, je-li batoh plny a nelze tak do nej pridat dalsi predmety
	 */
	public boolean jePlny()
	{
		return kapacita <= predmety.size();
	}
	
	/**
	 * metoda vlozi predmet do batohu
	 * @param predmet
	 */
	public void vlozPredmet(Predmet predmet) 
	{
		predmety.put(predmet.getNazev(), predmet);	
		this.setChanged();
	    this.notifyObservers();
	}
	
	
	/**
	 * vyjme predmet z batohu a zanecha ho na aktualni lokaci
	 * @param predmet
	 * @param lokace
	 */
	public void odlozPredmet(Predmet predmet, Lokace lokace)
	{		
		if (predmety.containsKey(predmet.getNazev())) 
		{
			predmety.remove(predmet.getNazev());
			lokace.vlozPredmet(predmet);	
			this.setChanged();
		    this.notifyObservers();
		} 

	}

	/**
	 * 
	 * @param predmet
	 * @return vraci true, pokud se predmet naleza v batohu
	 */
	public boolean obsahujePredmet(String nazev) {
		return predmety.containsKey(nazev);
	}
	
	
    /**
     * Najde v batohu předmět s daným názvem a vrátí ho. Pokud v batohu předmět s daným
     * názvem není, vrátí null.
     * 
     * @param      nazevPredmetu název předmětu
     * @returns    předmět
     */
    public Predmet najdiPredmet(String nazevPredmetu)
    {
        return predmety.get(nazevPredmetu);
    }

   
	@Override
	public String toString() 
	{		
		if (predmety.isEmpty()) 
		{
			return "Batoh je prázdný";
		}
		else
		{
			String seznam = "V batohu je uloženo " + predmety.size() + " věcí: ";
			
	        for (Predmet predmet : predmety.values())
	        {
	        	seznam += predmet.getNazev() + " ";
	        }	
	        
	        return seznam;
		}   
	}
	
	
	public List<Predmet> getPredmety(){
		
		List<Predmet> list = new ArrayList<>();
        for (Predmet predmet : predmety.values()){
        	list.add(predmet);
        }	
        // seradime, aby se vzdy vracelo stejne poradi
        Collections.sort(list);
       
        return list;
	}

	
	public int getKapacita() {
		return kapacita;
	}
	
}
