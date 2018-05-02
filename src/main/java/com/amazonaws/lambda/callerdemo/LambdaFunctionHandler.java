package com.amazonaws.lambda.callerdemo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.amazonaws.services.s3.model.S3Object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.concurrent.Future;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.lambda.AWSLambdaAsync;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;

import processor.CallerRequest;

public class LambdaFunctionHandler implements RequestHandler<CallerRequest, String> {

	@Override
	public String handleRequest(CallerRequest input, Context context) {
/*		long startTime = System.currentTimeMillis();
		context.getLogger().log("Hello for Caller" + input.getId());
		String function_input = "{\"id\":\" " + input.getId() + "AWS SDK for Java\"}";
		System.out.println(System.currentTimeMillis()-startTime);
		BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAJCW6LJ5HOGVDMZWQ", "07pWUYHLXwPVhLDI46d7bkBQGeCov6Rm7A0x1Rxl");
		System.out.println(System.currentTimeMillis()-startTime);
		AWSLambdaAsync lambda = AWSLambdaAsyncClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
		System.out.println(System.currentTimeMillis()-startTime);
		InvokeRequest req = new InvokeRequest()
				.withFunctionName("arn:aws:lambda:us-east-1:820393275086:function:javacaller")
				.withPayload(ByteBuffer.wrap(function_input.getBytes()));
		System.out.println(System.currentTimeMillis()-startTime);
		Future<InvokeResult> future_res = lambda.invokeAsync(req, new AsyncLambdaHandler());
		System.out.println(System.currentTimeMillis()-startTime);
		System.out.print("Waiting for async callback");
		while (!future_res.isDone() && !future_res.isCancelled()) {
			// perform some other tasks...
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.err.println("Thread.sleep() was interrupted!");
				System.exit(0);
			}
			System.out.print(".");
		}
		System.out.println(System.currentTimeMillis()-startTime);*/
		 long startTime = System.currentTimeMillis();
	        String clientRegion = "us-east-1";
	        String bucketName = "rogueagent-bucket1";
	        String key = "sample.json";
			BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAJCW6LJ5HOGVDMZWQ",
					"07pWUYHLXwPVhLDI46d7bkBQGeCov6Rm7A0x1Rxl");
	        try {
	        	System.out.println("1-"+(System.currentTimeMillis()-startTime));
	            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
	                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
	                    .withRegion(clientRegion)
	                    .build();
	            System.out.println("2-"+(System.currentTimeMillis()-startTime));
	            // Get an object and print its contents.
	            System.out.println("Downloading an object");
	            S3Object s3object = s3Client.getObject(new GetObjectRequest(bucketName, key));
	            System.out.println("3-"+(System.currentTimeMillis()-startTime));
	            System.out.println("Content-Type: " + s3object.getObjectMetadata().getContentType());
	            System.out.println("Content: ");
					displayTextInputStream(s3object.getObjectContent());
	            System.out.println("4-"+(System.currentTimeMillis()-startTime));
	            // Get a range of bytes from an object and print the bytes.
	            GetObjectRequest rangeObjectRequest = new GetObjectRequest(bucketName, key)
	                                                        .withRange(0,9);
	            S3Object objectPortion = s3Client.getObject(rangeObjectRequest);
	            System.out.println("Printing bytes retrieved.");
	            displayTextInputStream(objectPortion.getObjectContent());
	            
	            // Get an entire object, overriding the specified response headers, and print the object's content.
	            ResponseHeaderOverrides headerOverrides = new ResponseHeaderOverrides()
	                                                            .withCacheControl("No-cache")
	                                                            .withContentDisposition("attachment; filename=sample.json");
	            GetObjectRequest getObjectRequestHeaderOverride = new GetObjectRequest(bucketName, key)
	                                                            .withResponseHeaders(headerOverrides);
	            S3Object headerOverrideObject = s3Client.getObject(getObjectRequestHeaderOverride);
	        }
	        catch(AmazonServiceException e) {
	            // The call was transmitted successfully, but Amazon S3 couldn't process 
	            // it, so it returned an error response.
	            e.printStackTrace();
	        }
	        catch(SdkClientException e) {
	            // Amazon S3 couldn't be contacted for a response, or the client
	            // couldn't parse the response from Amazon S3.
	            e.printStackTrace();
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return "Bye from caller after "+(System.currentTimeMillis()-startTime)+input.getId();
	}

    private static void displayTextInputStream(InputStream input) throws IOException {
        // Read the text input stream one line at a time and display each line.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println();
    }
	
/*	private class AsyncLambdaHandler implements AsyncHandler<InvokeRequest, InvokeResult> {
		public void onSuccess(InvokeRequest req, InvokeResult res) {
			System.out.println("\nLambda function returned:");
			ByteBuffer response_payload = res.getPayload();
			System.out.println(new String(response_payload.array()));
		}

		public void onError(Exception e) {
			System.out.println(e.getMessage());
		}
	}*/
}