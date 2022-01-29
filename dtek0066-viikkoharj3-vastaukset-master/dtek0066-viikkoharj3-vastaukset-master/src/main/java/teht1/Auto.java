package teht1;

public abstract class Auto {

	protected boolean ehja;

	public Auto(){ this.ehja = true; }


	public void korjaa(){
		this.ehja = true;
	}

	public abstract boolean aja(Matka matka);
}
