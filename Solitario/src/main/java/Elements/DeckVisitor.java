package Elements;

public interface DeckVisitor {

    void visit(Stock s);
    void visit(Foundation f);
    void visit(Column c);

}
