package com.zia.androidpopupprojectwithlist;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

@SuppressLint({ "InflateParams", "DefaultLocale" })
public class EntryAdapter extends ArrayAdapter<item> implements Filterable {

	private Context context;
<<<<<<< HEAD
	private ArrayList<item> items, planetListnew, planetListorig;
	private LayoutInflater vi;

=======
	private ArrayList<item> items;
	private LayoutInflater vi;
	private List<item> planetList;
	private List<item> origPlanetList;
>>>>>>> FETCH_HEAD
	private Filter planetFilter;

	public EntryAdapter(Context context, ArrayList<item> items) {
		super(context, 0, items);
		this.context = context;
		this.items = items;
<<<<<<< HEAD
//		this.items = (ArrayList<item>) items;

		this.planetListnew = new ArrayList<item>();
		this.planetListorig = (ArrayList<item>) items;
=======
		this.planetList = items;
		this.origPlanetList = planetList;
>>>>>>> FETCH_HEAD
		vi = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

<<<<<<< HEAD
	public ArrayList<item> getEntries() {
		return items;
	}

	public void setEntries(ArrayList<item> entries) {
		this.items = entries;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public item getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	// public int getCount() {
	// return planetList.size();
	// }
	//
	// public item getItem(int position) {
	// return planetList.get(position);
	// }
	//
	// public long getItemId(int position) {
	// return planetList.get(position).hashCode();
	// }

=======
	public int getCount() {
		return planetList.size();
	}

	public item getItem(int position) {
		return planetList.get(position);
	}

	public long getItemId(int position) {
		return planetList.get(position).hashCode();
	}

>>>>>>> FETCH_HEAD
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		PlanetHolder holder = new PlanetHolder();
		final item i = items.get(position);
		if (i != null) {
			if (i.isSection()) {
				SectionItem si = (SectionItem) i;
				v = vi.inflate(R.layout.list_item_section, null);

				v.setOnClickListener(null);
				v.setOnLongClickListener(null);
				v.setLongClickable(false);

				final TextView sectionView = (TextView) v
						.findViewById(R.id.list_item_section_text);
				sectionView.setText(si.getTitle());

			} else {
				EntryItem ei = (EntryItem) i;
				v = vi.inflate(R.layout.list_item, null);

				final TextView title = (TextView) v
						.findViewById(R.id.product_name);
				holder.planetNameView = title;
				v.setTag(holder);
				holder = (PlanetHolder) v.getTag();

<<<<<<< HEAD
				item p = items.get(position);
=======
				item p = planetList.get(position);
>>>>>>> FETCH_HEAD
				holder.planetNameView.setText(p.getTitle());
				if (title != null)
					title.setText(ei.title);

			}
		}
		return v;
	}

	public void resetData() {
<<<<<<< HEAD
		items = planetListorig;
=======
		planetList = origPlanetList;
>>>>>>> FETCH_HEAD
	}

	/* *********************************
	 * We use the holder pattern It makes the view faster and avoid finding the
	 * component *********************************
	 */

	private static class PlanetHolder {
		public TextView planetNameView;

	}

	/*
	 * We create our filter
	 */

	@Override
	public Filter getFilter() {
		if (planetFilter == null)
			planetFilter = new PlanetFilter();

		return planetFilter;
	}

	private class PlanetFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();
			// We implement here the filter logic
			if (constraint == null || constraint.length() == 0) {
				// No filter implemented we return all the list
<<<<<<< HEAD
				results.values = planetListnew;
				results.count = planetListnew.size();
=======
				results.values = origPlanetList;
				results.count = origPlanetList.size();
>>>>>>> FETCH_HEAD
			} else {
				// We perform filtering operation
				List<item> nPlanetList = new ArrayList<item>();

<<<<<<< HEAD
				for (item p : items) {
=======
				for (item p : planetList) {
>>>>>>> FETCH_HEAD
					if (p.getTitle().toUpperCase()
							.startsWith(constraint.toString().toUpperCase()))
						nPlanetList.add(p);
				}

				results.values = nPlanetList;
				results.count = nPlanetList.size();

			}
			return results;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {

			// Now we have to inform the adapter about the new list filtered
			if (results.count == 0)
				notifyDataSetInvalidated();
			else {
<<<<<<< HEAD
				items = (ArrayList<item>) results.values;
=======
				planetList = (List<item>) results.values;
>>>>>>> FETCH_HEAD
				notifyDataSetChanged();
			}

		}

	}
}