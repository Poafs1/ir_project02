//Name: Rattanavaree Muangamai - Pantita Wang - Prach Yothaprasert
//Section: 3
//ID: 6088092 - 6088219 - 6088234

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.apache.commons.io.FileUtils;

public class SearcherEvaluator {
	private List<Document> queries = null;				//List of test queries. Each query can be treated as a Document object.
	private  Map<Integer, Set<Integer>> answers = null;	//Mapping between query ID and a set of relevant document IDs
	
	public List<Document> getQueries() {
		return queries;
	}

	public Map<Integer, Set<Integer>> getAnswers() {
		return answers;
	}

	/**
	 * Load queries into "queries"
	 * Load corresponding documents into "answers"
	 * Other initialization, depending on your design.
	 * @param corpus
	 */
	public SearcherEvaluator(String corpus)
	{
		String queryFilename = corpus+"/queries.txt";
		String answerFilename = corpus+"/relevance.txt";
		
		//load queries. Treat each query as a document. 
		this.queries = Searcher.parseDocumentFromFile(queryFilename);
		this.answers = new HashMap<Integer, Set<Integer>>();
		//load answers
		try {
			List<String> lines = FileUtils.readLines(new File(answerFilename), "UTF-8");
			for(String line: lines)
			{
				line = line.trim();
				if(line.isEmpty()) continue;
				String[] parts = line.split("\\t");
				Integer qid = Integer.parseInt(parts[0]);
				String[] docIDs = parts[1].trim().split("\\s+");
				Set<Integer> relDocIDs = new HashSet<Integer>();
				for(String docID: docIDs)
				{
					relDocIDs.add(Integer.parseInt(docID));
				}
				this.answers.put(qid, relDocIDs);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Returns an array of 3 numbers: precision, recall, F1, computed from the top *k* search results 
	 * returned from *searcher* for *query*
	 * @param query
	 * @param searcher
	 * @param k
	 * @return
	 */
	public double[] getQueryPRF(Document query, Searcher searcher, int k)
	{
		/*********************** YOUR CODE HERE *************************/
//		get query id of document
		int docID = query.getId(); // -> answers.get(docID)
//		search a query with input search methods
		List<SearchResult> listOfSearch = new ArrayList<SearchResult>(searcher.search(query.getRawText(), k));
		List<Integer> searchResultID = new ArrayList<Integer>();
//		get all search result id
		for (int i = 0; i < listOfSearch.size(); i++) {
			searchResultID.add(listOfSearch.get(i).getDocument().getId());
		}

//		intersec search result id with a id of revelance document
		List<Integer> intersecDocument = new ArrayList<Integer>(answers.get(docID));
		intersecDocument.retainAll(searchResultID);

//		compute precision, recall, and f1
		double precision = Math.abs(new Double(intersecDocument.size())) / Math.abs(new Double(searchResultID.size()));
		double recall = Math.abs(new Double(intersecDocument.size()))/ Math.abs(new Double(answers.get(docID).size()));
		double f1 = (2 * precision * recall) / (precision + recall);
//		if the value is NaN -> use 0.0
		if (Double.isNaN(f1)) f1 = 0.0;

//		store result in array size of 3
		double[] result = {precision, recall, f1};

		return result;
		/****************************************************************/
	}
	
	/**
	 * Test all the queries in *queries*, from the top *k* search results returned by *searcher*
	 * and take the average of the precision, recall, and F1. 
	 * @param searcher
	 * @param k
	 * @return
	 */
	public double[] getAveragePRF(Searcher searcher, int k)
	{
		/*********************** YOUR CODE HERE *************************/
//		queries
		double precision = 0.0;
		double recall = 0.0;
		double f1 = 0.0;
//		loop all queries
		for(int i=0; i<queries.size(); i++) {
//			pass each query to getQueryPRF function -> return array of 3 {precision, recall, f1}
			double[] prf = getQueryPRF(queries.get(i), searcher, k);
//			∑ all of precision recall and f1
			precision += prf[0];
			recall += prf[1];
			f1 += prf[2];
		}

//		Calculate average of precision, recall and f1
		double precisionAvg = (1 / new Double(queries.size())) * precision;
		double recallAvg = (1 / new Double(queries.size())) * recall;
		double f1Avg = (1 / new Double(queries.size())) * f1;

//		return result of precision average, recall average and f1 average in array of length 3
		double[] result = {precisionAvg, recallAvg, f1Avg};

		return result;
		/****************************************************************/
	}
}
