// Patrick Cain
// 7/6/15
// Flickr Coding Challenge

import java.awt.Desktop;
import java.net.URL;
import java.util.Scanner;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photos.SearchParameters;

public class Main {
	// One enhancement I didn't get around to is making the main function a
	// stub,
	// which would call a sequence of functions
	public static void main(String[] args) throws FlickrException {
		// my flickr api info
		String apiKey = "1e35642e7e515bd2cc900c8072b4e218";
		String sharedSecret = "91846b0bc921eec4";

		// TODO: add functionality for inputting multiple tags
		String[] tags = new String[1];
		// Prompts user to input the tag they want to search by
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter tag to search! ");
		tags[0] = scan.nextLine();

		// declare and initialize useful objects
		Flickr flickr = new Flickr(apiKey, sharedSecret, new REST());

		SearchParameters searchParams = new SearchParameters();
		// specifies that search will be based upon tag
		searchParams.setTags(tags);

		PhotosInterface photoInt = new PhotosInterface(apiKey, sharedSecret,
				new REST());
		// create PhotoList to store photo objects
		PhotoList<Photo> picList = new PhotoList();
		picList = flickr.getPhotosInterface().search(searchParams, 0, 0);

		// helper variables
		int i = 0;
		String path = "http://www.lotame.com/"; // initialized so Lotame is
												// default
		int decision = 0;

		// Loop to control list of photos
		while (i < picList.size()) {

			// Print lines to show basic meta-info and to grab url
			System.out.println("Title: " + picList.get(i).getTitle());
			System.out.println("Date Taken: " + picList.get(i).getDateTaken());
			System.out.println("Owner: " + picList.get(i).getOwner());
			System.out.println("photoID: " + picList.get(i).getId());
			System.out.println("Secret: " + picList.get(i).getSecret());
			path = picList.get(i).getUrl();
			System.out.println("URL: " + path);

			// prompt to allow user to move to next picture, quit the stream or
			// view the current photo
			System.out.println("Enter 0 (Quit), 1 (Next), 2 (View)");
			decision = scan.nextInt();
			System.out
					.println("===============================================================");

			// switch statement for handling user input
			switch (decision) {
			case 0:
				System.out.println("Exited Photo Stream");
				i = picList.size();
				break;
			case 1:
				i++;
				System.out.println("Next... ");
				break;
			case 2:
				// opens url in browser window
				try {
					Desktop.getDesktop().browse(new URL(path).toURI());
				} catch (Exception e) {
					System.out.println("URL invalid, try another photo!");
				}
				break;
			default:
				i++;
				break;
			}
		}

	}
}