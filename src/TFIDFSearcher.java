//Name: Rattanavaree Muangamai - Pantita Wang - Prach Yothaprasert
//Section: 3
//ID: 6088092 - 6088219 - 6088234

import java.util.*;

public class TFIDFSearcher extends Searcher
{
	private List<Document> doc;
	HashMap<String, Double> documentsIDF = new HashMap<String, Double>();
	private List<HashMap<String, Double>> documentsWeight = new ArrayList<HashMap<String, Double>>();

	public TFIDFSearcher(String docFilename) {
		super(docFilename);
		/************* YOUR CODE HERE ******************/
		this.doc = this.documents;

//		Get all term from documents
//		Get document terms and document frequency (df) of documents
		HashMap<String, Integer> mapDocumentsTerms = new HashMap<String, Integer>();
		for(int i=0; i<doc.size(); i++) {
			List<String> tokens = doc.get(i).getTokens();
			Set<String> documentTerms = new HashSet<String>(tokens);
			documentTerms.forEach(d -> {
				if(mapDocumentsTerms.containsKey(d)) {
					int currentValue = mapDocumentsTerms.get(d);
					mapDocumentsTerms.replace(d, currentValue + 1);
				} else {
					mapDocumentsTerms.put(d, 1);
				}
			});
		}
//		System.out.println(mapDocumentsTerms); -> eg. {cancel=7, ccc=2, illumin=9, gloomi=3, descriptor=21}

//		Compute Inverse Document Frequency (IDF) from df of each document
		mapDocumentsTerms.keySet().forEach(m -> {
//			m -> keys / mapDocumentsTerms.get(m) -> values
			int freq = mapDocumentsTerms.get(m);
			double cal = 0;
			if(freq > 0) cal = 1 + (new Double(doc.size()) / new Double(freq));
			documentsIDF.put(m, Math.log10(cal));
		});
//		System.out.println(documentsIDF); -> {cancel=2.9334872878487053, ccc=3.477193631102115, illumin=2.824487414556787}

//		Compute Term Frequency (TF) and documents weight (TFIDF) from each document
		for(int i=0; i<doc.size(); i++) {
			List<String> tokens = new ArrayList<String>(doc.get(i).getTokens());
			HashMap<String, Double> storeTFIDF = new HashMap<String, Double>();
			tokens.forEach(t -> {
				double getTF = 0.0;
				int freq = Collections.frequency(tokens, t);
				if (freq > 0) getTF = 1 + Math.log10(new Double(freq));
				double getTFIDF = documentsIDF.get(t) * getTF;
				storeTFIDF.put(t, getTFIDF);
			});
			documentsWeight.add(storeTFIDF);
		}

		/***********************************************/
	}

	@Override
	public List<SearchResult> search(String queryString, int k) {
		/************* YOUR CODE HERE ******************/
//		Get token from queryString
		List<String> tokenizeQuery = Searcher.tokenize(queryString); // all query
//		Get unique term from tokenizeQuery
		Set<String> queryTerms = new HashSet<String>(tokenizeQuery); // uniq token
		List<SearchResult> allResults = new ArrayList<SearchResult>();
		List<SearchResult> output = new ArrayList<SearchResult>();

		for(int i=0; i<doc.size(); i++) {
			HashMap<String, Double> queryWeight = new HashMap<String, Double>();
			queryTerms.forEach(q -> {
				double getIDF = documentsIDF.get(q);
				double getTF = 0.0;
				int freq = Collections.frequency(tokenizeQuery, q);
				if (freq > 0) getTF = 1 + Math.log10(new Double(freq));
				double getTFIDF = getIDF * getTF;
				queryWeight.put(q, getTFIDF);
			});

			double dotProd = 0.0;
			for (String d : documentsWeight.get(i).keySet()) {
				if (queryWeight.containsKey(d)) {
					dotProd += documentsWeight.get(i).get(d) * queryWeight.get(d);
				}
			}

			double sumq = 0.0;
			for(String q : queryWeight.keySet()) {
				double value = queryWeight.get(q);
				sumq += Math.pow(value, 2);
			}

			double sumd = 0.0;
			for (String d : documentsWeight.get(i).keySet()) {
				double value = documentsWeight.get(i).get(d);
				sumd += Math.pow(value, 2);
			}

			double calculateScore = dotProd / (Math.sqrt(sumq) * Math.sqrt(sumd));
			SearchResult getResult = new SearchResult(doc.get(i), calculateScore);
			allResults.add(getResult);
		}

		Collections.sort(allResults);
		for(int i=0; i<k; i++) {
			output.add(allResults.get(i));
		}

		return output;
		/***********************************************/
	}
}