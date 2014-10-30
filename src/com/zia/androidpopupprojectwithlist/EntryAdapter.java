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
	private ArrayList<item> items, planetListnew, planetListorig;
	private LayoutInflater vi;

	private Filter planetFilter;

	public EntryAdapter(Context context, ArrayList<item> items) {
		super(context, 0, items);
		this.context = context;
		this.items = items;
//		this.items = (ArrayList<item>) items;

		this.planetListnew = new ArrayList<item>();
		this.planetListorig = (ArrayList<item>) items;
		vi = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

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

				item p = items.get(position);
				holder.planetNameView.setText(p.getTitle());
				if (title != null)
					title.setText(ei.title);

			}
		}
		return v;
	}

	public void resetData() {
		items = planetListorig;
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
				results.values = planetListnew;
				results.count = planetListnew.size();
			} else {
				// We perform filtering operation
				List<item> nPlanetList = new ArrayList<item>();

				for (item p : items) {
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
				items = (ArrayList<item>) results.values;
				notifyDataSetChanged();
			}

		}

	}
}