package teht1;

public class OmaAuto extends Auto{

	public OmaAuto() { super(); }

	@Override
	public boolean aja(Matka matka){
		super.ehja &= new java.util.Random().nextBoolean();
		return super.ehja;
	}

}
