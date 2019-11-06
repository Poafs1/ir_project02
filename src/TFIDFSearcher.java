//Name: Rattanavaree Muangamai - Pantita Wang - Prach Yothaprasert
//Section: 3
//ID: 6088092 - 6088219 - 6088234

import java.util.*;

public class TFIDFSearcher extends Searcher
{
	private List<Document> doc;
	HashMap<String, Integer> mapDoc = new HashMap<>();
	HashMap<String, Double> docIDF = new HashMap<>();
	List<List<Double>> docTFIDF = new ArrayList<>();
	public TFIDFSearcher(String docFilename) {
		super(docFilename);
		/************* YOUR CODE HERE ******************/
		this.doc = this.documents;
		for(int i=0; i<doc.size(); i++) {
			Set<String> docTerms = new HashSet<String>(doc.get(i).getTokens());
			for(String d : docTerms) {
				if(mapDoc.containsKey(d)) {
					mapDoc.replace(d, mapDoc.get(d) + 1);
				} else {
					mapDoc.put(d, 1);
				}
			}
		}

		for(String m : mapDoc.keySet()) {
//			m -> key / mapDoc.get(m) -> value
			int freq = mapDoc.get(m);
			double cal = 0;
			if(freq > 0) cal = 1 + (new Double(doc.size()) / new Double(freq));
			docIDF.put(m, Math.log10(cal));
		}

		for(int i=0; i<doc.size(); i++) {
			List<String> token = new ArrayList<String>(doc.get(i).getTokens());
			List<Double> store = new ArrayList<Double>();
			for(String t : token) {
				double getTF = 0.0;
				int freq = Collections.frequency(token, t);
				if(freq > 0) getTF = 1 + Math.log10(new Double(freq));
				double getTFIDF = docIDF.get(t) * getTF;
				store.add(getTFIDF);
			}
			docTFIDF.add(store);
		}
		/***********************************************/
	}

	@Override
	public List<SearchResult> search(String queryString, int k) {
		/************* YOUR CODE HERE ******************/
		List<String> tokens = Searcher.tokenize(queryString);
		Set<String> queryTerms = new HashSet<String>(tokens);
		List<Double> queryTFIDF = new ArrayList<Double>();
		List<SearchResult> allResults = new ArrayList<SearchResult>();
		List<SearchResult> output = new ArrayList<SearchResult>();

		for(String q : queryTerms) {
			double getIDF = inverseDocumentFrequency(q);
			for(int i=0; i<doc.size(); i++) {
				double getTF = termFrequency(q, doc.get(i).getTokens());
				double getTFIDF = getTF * getIDF;
				if(queryTFIDF.size() > i) {
					double getCurrentTFIDF = queryTFIDF.get(i);
					queryTFIDF.set(i, getCurrentTFIDF + getTFIDF);
				} else {
					queryTFIDF.add(getTFIDF);
				}
			}
		}

		System.out.println(queryTFIDF.size());

		return null;
//		for(int i=0; i<doc.size(); i++) {
//			SearchResult getResult = new SearchResult(doc.get(i), queryTFIDF.get(i));
//			allResults.add(getResult);
//		}
//
//		Collections.sort(allResults);
//		for(int i=0; i<k; i++) {
//			output.add(allResults.get(i));
//		}
//
//		return output;
		/***********************************************/
	}

//	Receive term and document x
	public double termFrequency(String term, List<String> document) {
		int freq = Collections.frequency(document, term);
		double cal = 0;
		if(freq > 0) cal = 1 + Math.log10(new Double(freq));
		return cal;
	}

	public double inverseDocumentFrequency(String term) {
		int freq = 0;
		for (int i = 0; i < doc.size(); i++) {
			if(doc.get(i).getTokens().contains(term)) freq++;
		}
		double cal = 1 + (new Double(doc.size()) / new Double(freq));
		return Math.log10(cal);
	}
}