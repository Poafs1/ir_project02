//Name: Rattanavaree Muangamai - Pantita Wang - Prach Yothaprasert
//Section: 3
//ID: 6088092 - 6088219 - 6088234

import java.util.*;

public class TFIDFSearcher extends Searcher
{
	private List<Document> doc;
	public TFIDFSearcher(String docFilename) {
		super(docFilename);
		/************* YOUR CODE HERE ******************/
		this.doc = this.documents;
		/***********************************************/
	}
	
	@Override
	public List<SearchResult> search(String queryString, int k) {
		/************* YOUR CODE HERE ******************/
		List<String> tokens = Searcher.tokenize(queryString);
		Set<String> queryTerms = new HashSet<String>(tokens);
		List<Double> tf = new ArrayList<Double>();

//		query terms -> eg. [inform, retriev]
//		found tf
		for(int i=0; i<doc.size(); i++) {
			int countWord = 0;
			for(String q : queryTerms) {
				countWord += Collections.frequency(doc.get(i).getTokens(), q);
			}
			double foundTF = 1 + Math.log10(countWord);
			tf.add(foundTF);
		}

//		found idf




		System.out.println();
		return null;
		/***********************************************/
	}
}

//int countWord = Collections.frequency(doc.get(i).getTokens(), q);

//		for(String q : queryTerms) {
//				int totalDoc = doc.size();
//				int df = 0;
//				for(int i=0; i<doc.size(); i++) {
//		if (doc.get(i).getTokens().contains(q)) df++;
//		}
//		double idf = Math.log10(1 + (new Double(totalDoc) / new Double(df)));
////			System.out.println(idf);
//		df = 0;
//		}