//Name: Rattanavaree Muangamai - Pantita Wang - Prach Yothaprasert
//Section: 3
//ID: 6088092 - 6088219 - 6088234

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

		for(int i=0; i<doc.size(); i++) {
			Collections.sort(doc.get(i).getTokens());
			int totalTerms = doc.get(i).getTokens().size();
			List<String> tokens = new ArrayList<>(doc.get(i).getTokens());
			List<Double> tf = new ArrayList<>();
			int count = 0;
			for(int j=0; j<totalTerms - 1; j++) {
				if(!tokens.get(j).equals(tokens.get(j + 1))) {
					count++;
					double a = new Double(count) / new Double(totalTerms);
					tf.add(a);
					count = 0;
				} else {
					count++;
				}
			}
		}


		return null;
		/***********************************************/
	}
}
