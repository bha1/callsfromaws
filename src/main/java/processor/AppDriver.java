package processor;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.nio.ByteBuffer;
import java.util.concurrent.Future;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambdaAsync;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import processor.CallerRequest;

public class AppDriver {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		System.out.println("Hello for Caller" + "4321");
	/*	String function_input = "{\"id\":\" " + "4321" + "AWS SDK for Java\"}";
		System.out.println(System.currentTimeMillis() - startTime);
		BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAJCW6LJ5HOGVDMZWQ",
				"07pWUYHLXwPVhLDI46d7bkBQGeCov6Rm7A0x1Rxl");
		System.out.println(System.currentTimeMillis() - startTime);
		AWSLambdaAsync lambda = AWSLambdaAsyncClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_1).build();
		System.out.println(System.currentTimeMillis() - startTime);
		InvokeRequest req = new InvokeRequest().withFunctionName("arn:aws:lambda:us-east-1:820393275086:function:hw1")
				.withPayload(ByteBuffer.wrap(function_input.getBytes()));
//		.withFunctionName("arn:aws:lambda:us-east-1:820393275086:function:javacaller")
		System.out.println(System.currentTimeMillis() - startTime);
		Future<InvokeResult> future_res = lambda.invokeAsync(req, new AsyncLambdaHandler());
		System.out.println(System.currentTimeMillis() - startTime);
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
		System.out.println(System.currentTimeMillis() - startTime);*/
		startTime=(System.currentTimeMillis() - startTime);
		System.out.println("Bye from caller after " + startTime + " - 4321");
	}
}