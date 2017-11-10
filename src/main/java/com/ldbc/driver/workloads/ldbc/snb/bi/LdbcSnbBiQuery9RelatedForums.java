package com.ldbc.driver.workloads.ldbc.snb.bi;

import com.ldbc.driver.Operation;
import com.ldbc.driver.SerializingMarshallingException;

import java.util.ArrayList;
import java.util.List;

public class LdbcSnbBiQuery9RelatedForums extends Operation<List<LdbcSnbBiQuery9RelatedForumsResult>>
{
    public static final int TYPE = 9;
    public static final int DEFAULT_LIMIT = 100;
    private final String tagClass1;
    private final String tagClass2;
    private final int threshold;
    private final int limit;

    public LdbcSnbBiQuery9RelatedForums(String tagClass1, String tagClass2, int threshold, int limit )
    {
        this.tagClass1 = tagClass1;
        this.tagClass2 = tagClass2;
        this.threshold = threshold;
        this.limit = limit;
    }

    public String tagClass1()
    {
        return tagClass1;
    }

    public String tagClass2()
    {
        return tagClass2;
    }

    public int threshold()
    {
        return threshold;
    }

    public int limit()
    {
        return limit;
    }

    @Override
    public String toString()
    {
        return "LdbcSnbBiQuery9RelatedForums{" +
               "tagClass1='" + tagClass1 + '\'' +
               ", tagClass2='" + tagClass2 + '\'' +
               ", threshold=" + threshold +
               ", limit=" + limit +
               '}';
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        { return true; }
        if ( o == null || getClass() != o.getClass() )
        { return false; }

        LdbcSnbBiQuery9RelatedForums that = (LdbcSnbBiQuery9RelatedForums) o;

        if ( threshold != that.threshold )
        { return false; }
        if ( limit != that.limit )
        { return false; }
        if ( tagClass1 != null ? !tagClass1.equals( that.tagClass1) : that.tagClass1 != null )
        { return false; }
        return !(tagClass2 != null ? !tagClass2.equals( that.tagClass2) : that.tagClass2 != null);
    }

    @Override
    public int hashCode()
    {
        int result = tagClass1 != null ? tagClass1.hashCode() : 0;
        result = 31 * result + (tagClass2 != null ? tagClass2.hashCode() : 0);
        result = 31 * result + threshold;
        result = 31 * result + limit;
        return result;
    }

    @Override
    public List<LdbcSnbBiQuery9RelatedForumsResult> marshalResult( String serializedResults )
            throws SerializingMarshallingException
    {
        List<List<Object>> resultsAsList = SerializationUtil.marshalListOfLists( serializedResults );
        List<LdbcSnbBiQuery9RelatedForumsResult> result = new ArrayList<>();
        for ( int i = 0; i < resultsAsList.size(); i++ )
        {
            List<Object> row = resultsAsList.get( i );
            long forumId = ((Number) row.get( 0 )).longValue();
            int sumA = ((Number) row.get( 1 )).intValue();
            int sumB = ((Number) row.get( 2 )).intValue();
            result.add(
                    new LdbcSnbBiQuery9RelatedForumsResult(
                            forumId,
                            sumA,
                            sumB
                    )
            );
        }
        return result;
    }

    @Override
    public String serializeResult( Object resultsObject ) throws SerializingMarshallingException
    {
        List<LdbcSnbBiQuery9RelatedForumsResult> result = (List<LdbcSnbBiQuery9RelatedForumsResult>) resultsObject;
        List<List<Object>> resultsFields = new ArrayList<>();
        for ( int i = 0; i < result.size(); i++ )
        {
            LdbcSnbBiQuery9RelatedForumsResult row = result.get( i );
            List<Object> resultFields = new ArrayList<>();
            resultFields.add( row.forumId() );
            resultFields.add( row.sumA() );
            resultFields.add( row.sumB() );
            resultsFields.add( resultFields );
        }
        return SerializationUtil.toJson( resultsFields );
    }

    @Override
    public int type()
    {
        return TYPE;
    }
}
