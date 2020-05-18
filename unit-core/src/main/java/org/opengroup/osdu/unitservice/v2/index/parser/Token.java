package org.opengroup.osdu.unitservice.v2.index.parser;

/**
 * A class represents a token. Tokens in keyword string are separated by white space.
 * However, a token could contains white space quoted by {@link PhraseQuote}.
 */
public class Token {
    private Object object;
    private int startPosition;
    private int endPosition;

    /**
     * Constructor
     * @param obj the object that token represents
     * @param startPosition the start position of the token in the keyword string
     * @param endPosition the end position of the token in the keyword string
     */
    public Token(Object obj, int startPosition, int endPosition)
    {
        this.object = obj;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    /**
     * Gets the object of the token
     * @return object of the token
     */
    public Object getObject() { return this.object; }

    /**
     * Gets start position of the token in the keyword string
     * @return start position of the token in the keyword string
     */
    public int getStartPosition() { return this.startPosition; }

    /**
     * Gets end position of the token in the keyword string
     * @return end position of the token in the keyword string
     */
    public int getEndPosition() { return this.endPosition; }

    /**
     * Checks whether the token is a 'OR' operator
     * @return true if it is a 'OR' operator; otherwise, false
     */
    public boolean isOrOperator()
    {
        return OrOperator.isOrOperator(toString());
    }

    /**
     * Checks whether the token is a excluded operator
     * @return true if it is a excluded operator; otherwise, false
     */
    public boolean isExcludedOperator()
    {
        char[] chars = toString().toCharArray();
        if(chars.length != 1) return false;

        return ExcludedOperator.isExcludedOperator(chars[0]);
    }

    /**
     * Checks whether the token is a field operator
     * @return true if it is a field operator; otherwise, false
     */
    public boolean isFieldOperator()
    {
        char[] chars = toString().toCharArray();
        if(chars.length != 1) return false;

        return FieldOperator.isFieldOperator(chars[0]);
    }

    /**
     * Checks whether the token is a phrase quote operator
     * @return true if it is a phrase quote operator; otherwise, false
     */
    public boolean isPhraseQuote()
    {
        char[] chars = toString().toCharArray();
        if(chars.length != 1) return false;

        return PhraseQuote.isPhraseQuote(chars[0]);
    }

    @Override
    public String toString()
    {
        return this.object.toString();
    }
}
