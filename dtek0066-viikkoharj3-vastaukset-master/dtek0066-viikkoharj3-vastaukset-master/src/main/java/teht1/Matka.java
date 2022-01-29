package teht1;

public class Matka {

	private float matkaKM;

	public Matka(float matka) {
		this.matkaKM = matka;
	}

	public float getMatkaKM() {
		return this.matkaKM;
	}

	public String toString() {
		return Float.toString(matkaKM) + " km";
	}
}
