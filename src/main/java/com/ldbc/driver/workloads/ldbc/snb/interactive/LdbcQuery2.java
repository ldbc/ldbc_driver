package com.ldbc.driver.workloads.ldbc.snb.interactive;

import com.ldbc.driver.Operation;
import com.ldbc.driver.SerializingMarshallingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LdbcQuery2 extends Operation<List<LdbcQuery2Result>> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static final int DEFAULT_LIMIT = 20;
    private final long personId;
    private final String personUri;
    private final Date maxDate;
    private final int limit;

    public LdbcQuery2(long personId, String personUri, Date maxDate, int limit) {
        super();
        this.personId = personId;
        this.personUri = personUri;
        this.maxDate = maxDate;
        this.limit = limit;
    }

    public long personId() {
        return personId;
    }

    public String personUri() {
        return personUri;
    }

    public Date maxDate() {
        return maxDate;
    }

    public int limit() {
        return limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LdbcQuery2 that = (LdbcQuery2) o;

        if (limit != that.limit) return false;
        if (personId != that.personId) return false;
        if (maxDate != null ? !maxDate.equals(that.maxDate) : that.maxDate != null) return false;
        if (personUri != null ? !personUri.equals(that.personUri) : that.personUri != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (personId ^ (personId >>> 32));
        result = 31 * result + (personUri != null ? personUri.hashCode() : 0);
        result = 31 * result + (maxDate != null ? maxDate.hashCode() : 0);
        result = 31 * result + limit;
        return result;
    }

    @Override
    public String toString() {
        return "LdbcQuery2{" +
                "personId=" + personId +
                ", personUri='" + personUri + '\'' +
                ", maxDate=" + maxDate +
                ", limit=" + limit +
                '}';
    }

    @Override
    public List<LdbcQuery2Result> marshalResult(String serializedResults) throws SerializingMarshallingException {
        List<List<Object>> resultsAsList;
        try {
            resultsAsList = objectMapper.readValue(serializedResults, new TypeReference<List<List<Object>>>() {
            });
        } catch (IOException e) {
            throw new SerializingMarshallingException(String.format("Error while parsing serialized results\n%s", serializedResults), e);
        }

        List<LdbcQuery2Result> results = new ArrayList<>();
        for (int i = 0; i < resultsAsList.size(); i++) {
            List<Object> resultAsList = resultsAsList.get(i);
            long personId = ((Number) resultAsList.get(0)).longValue();
            String personFirstName = (String) resultAsList.get(1);
            String personLastName = (String) resultAsList.get(2);
            long postOrCommentId = ((Number) resultAsList.get(3)).longValue();
            String postOrCommentContent = (String) resultAsList.get(4);
            long postOrCommentCreationDate = ((Number) resultAsList.get(5)).longValue();

            results.add(new LdbcQuery2Result(
                    personId,
                    personFirstName,
                    personLastName,
                    postOrCommentId,
                    postOrCommentContent,
                    postOrCommentCreationDate
            ));
        }

        return results;
    }

    @Override
    public String serializeResult(Object resultsObject) throws SerializingMarshallingException {
        List<LdbcQuery2Result> results = (List<LdbcQuery2Result>) resultsObject;
        List<List<Object>> resultsFields = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            LdbcQuery2Result result = results.get(i);
            List<Object> resultFields = new ArrayList<>();
            resultFields.add(result.personId());
            resultFields.add(result.personFirstName());
            resultFields.add(result.personLastName());
            resultFields.add(result.postOrCommentId());
            resultFields.add(result.postOrCommentContent());
            resultFields.add(result.postOrCommentCreationDate());
            resultsFields.add(resultFields);
        }

        try {
            return objectMapper.writeValueAsString(resultsFields);
        } catch (IOException e) {
            throw new SerializingMarshallingException(String.format("Error while trying to serialize result\n%s", results.toString()), e);
        }
    }
}