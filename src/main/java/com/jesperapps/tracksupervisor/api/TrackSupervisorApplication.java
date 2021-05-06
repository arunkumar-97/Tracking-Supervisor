package com.jesperapps.tracksupervisor.api;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.xbill.DNS.*;

import java.net.UnknownHostException;

@SpringBootApplication
public class TrackSupervisorApplication {

	public static void main(String[] args) throws TextParseException, UnknownHostException {
//		Scanner input = new Scanner(System.in);
//        System.out.print("Input the latitude of coordinate 1: ");
//        double lat1 = input.nextDouble();
//        System.out.print("Input the longitude of coordinate 1: ");
//        double lon1 = input.nextDouble();
//        System.out.print("Input the latitude of coordinate 2: ");
//        double lat2 = input.nextDouble();
//        System.out.print("Input the longitude of coordinate 2: ");
//        double lon2 = input.nextDouble();
//
//        lat1 = Math.toRadians(lat1);
//        lon1 = Math.toRadians(lon1);
//        lat2 = Math.toRadians(lat2);
//        lon2 = Math.toRadians(lon2);
//
//        double earthRadius = 6371.01; //Kilometers
//        double dist = earthRadius * Math.acos(Math.sin(lat1)*Math.sin(lat2) + Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon1 - lon2));
//        System.out.println("dist"+dist);
//        System.out.print("The distance between those points is: " + distance_Between_LatLong(lat1, lon1, lat2, lon2) + " km\n");
//		 java.util.Hashtable<String, String> env = new java.util.Hashtable<String, String>();
//		    env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
//
//		    try {
//		        javax.naming.directory.DirContext dirContext
//		            = new javax.naming.directory.InitialDirContext(env);
//		        javax.naming.directory.Attributes attrs
//		            = dirContext.getAttributes("jesperapps.com", new String[] { "TXT" });
//		        javax.naming.directory.Attribute attr
//		            = attrs.get("TXT");
//
//		        String txtRecord = "";
//
//		        if(attr != null) {
//		            txtRecord = attr.get().toString();
//		        }
//		        System.out.println("txtRecord"+txtRecord);
//		       // return txtRecord;
//
//		    } catch (javax.naming.NamingException e) {
//
//		        e.printStackTrace();
//		       // return "";
//		    }
		
		//String ipAddress = "";
//		String dnsblDomain = "jespersoft.com";
//
//		Lookup lookup = new Lookup(dnsblDomain, Type.TXT);
//		Resolver resolver = new SimpleResolver();
//		lookup.setResolver(resolver);
//		lookup.setCache(null);
//		Record[] records = lookup.run();
//		if (lookup.getResult() == Lookup.SUCCESSFUL) {
//			String responseMessage = null;
//			String listingType = null;
//			for (int i = 0; i < records.length; i++) {
//				if (records[i] instanceof TXTRecord) {
//					TXTRecord txt = (TXTRecord) records[i];
//					for (Iterator j = txt.getStrings().iterator(); j.hasNext();) {
//						responseMessage += (String) j.next();
//					}
//				} else if (records[i] instanceof ARecord) {
//					listingType = ((ARecord) records[i]).getAddress().getHostAddress();
//				}
//			}
//			System.err.println("Found!");
//			System.err.println("Response Message: " + responseMessage);
//			System.err.println("Listing Type: " + listingType);
//		} else if (lookup.getResult() == Lookup.HOST_NOT_FOUND) {
//			System.err.println("Not found.");
//		} else {
//			System.err.println("Error!");

//		final Lookup dnsLookup = new Lookup("google.com", Type.MX);
//	    return dnsLookup.run();

//		InetAddress inetAddress = InetAddress.getByName(name);
//		String address = inetAddress.getHostAddress();
//		// get the default initial Directory Context
//		InitialDirContext idc = new InitialDirContext();
//		// get the DNS records for inetAddress
//		Attributes attributes = idc.getAttributes("dns:/" + inetAddress.getHostName());
//		// get an enumeration of the attributes and print them out
//		NamingEnumeration attributeEnumeration = attributes.getAll();
//		System.out.println("-- DNS INFORMATION --");
//		while (attributeEnumeration.hasMore()) {
//		    System.out.println("" + attributeEnumeration.next());
//		}
//		attributeEnumeration.close();

//		InetAddress dnsresult[] = InetAddress.getAllByName("google.com");
//        for (int i=0; i<dnsresult.length; i++)
//        System.out.println (dnsresult[i]);

//		Hashtable env = new Hashtable();
//		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
//		env.put("java.naming.provider.url", "dns:");
//		DirContext ctx = new InitialDirContext(env);
//		
//		Attributes attributes = ctx.getAttributes("jesperapps.com", new String[]{"SRV"});
//		javax.naming.directory.Attribute a = attributes.get("SRV");
			SpringApplication.run(TrackSupervisorApplication.class, args);
//		}
	
	}
//	 public static double distance_Between_LatLong(double lat1, double lon1, double lat2, double lon2) {
//	        
//	    }

}
