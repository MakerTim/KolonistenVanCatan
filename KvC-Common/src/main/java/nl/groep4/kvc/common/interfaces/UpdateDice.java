package nl.groep4.kvc.common.interfaces;

public interface UpdateDice extends Updatable<Throw> {

    public void throwDice();

    public void updateDices(int dice1, int dice2);

}
