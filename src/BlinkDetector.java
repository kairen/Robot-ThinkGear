

import java.util.*; 

public class BlinkDetector {

	
   static   ArrayList<Integer> eegSampleBuffer = new ArrayList<Integer>();

    private static int eegSampleBufferSize = 75;

    private static int maximumDetectionSize = 40;  // was 65 before

    private static int minimumDetectionSize = 30;

    private static float maximumSlopeThreshold = 35.0f;
    
    private static float minimumSlopeThreshold = 15.0f;

    private static int minimumDeltaThreshold = 600; //800 before

    private static int maximumDeltaThreshold = 1400;

    private static int timeoutBlinkCounterLength = 128;
    
    private static int timeoutSpikeCounterLength = 512;

    private static int timeoutCounter;

    private static int counter = 0;

    public static int ArraySize = 0;
    
    private static int signalQualityClearThreshold = 30;

    
    public int getEegSampleBufferSize()
    {
        return eegSampleBufferSize;
    }
    
    
    public void setEegSampleBufferSize(int value)
        {
            eegSampleBufferSize = value;
            TruncateArray();
        }
    
    public int getMaximumDetectionSize()
    {
        
            return maximumDetectionSize;
     }
    public void setMaximumDetectionSize(int value )
        {
            maximumDetectionSize = value;
        }

    public int getMinimumDetectionSize()
    {
       
            return minimumDetectionSize;
    }
    
    public void  setMinimumDetectionSize(int value)
        {
            minimumDetectionSize = value;
        }

    public BlinkDetector()
    {
        eegSampleBuffer = new ArrayList<Integer>();
        timeoutCounter = 0;
    }
 
    private static float CalculateSlope(int firstX, int firstY, int secondX, int secondY)
    {
        return ((float)secondY - (float)firstY) / ((float)secondX - (float)firstX);
    }

    public static void AddEEGPoint(int point)
    {
        timeoutCounter--;
        counter++;
        timeoutCounter = timeoutCounter >= 0 ? timeoutCounter : 0;
        eegSampleBuffer.add(point);
        ArraySize =  eegSampleBuffer.size();
    }

    public static void TruncateArray()
    {
        if (eegSampleBuffer.size() > eegSampleBufferSize)
        {
        	eegSampleBuffer.subList(0, eegSampleBuffer.size()- eegSampleBufferSize).clear();
        }
    }
   
    public static void AddEEGPoints(ArrayList<Integer> points)
      {
          timeoutCounter -= points.size();
          counter += points.size();
          timeoutCounter = timeoutCounter >= 0 ? timeoutCounter : 0;
          eegSampleBuffer.addAll(points);
      }
    
    public static int FindBlink(double poorsignal)
    {
        int isBlink = 0;
        TruncateArray();
        float reportSlope = 0;
        
        int reportdelta = 0, reporttimeLength = 0;

//        System.out.println(poorsignal);
        
        if (poorsignal > signalQualityClearThreshold )
        {
            ClearEEGPoints();
            return 0;
        }
        if (timeoutCounter > 0 || eegSampleBuffer.size() == 0)
        {
            return 0;
            
        }

        int currentPoint = (int) eegSampleBuffer.get(eegSampleBuffer.size() - 1);
        if (eegSampleBuffer.size() >= minimumDetectionSize)
        {
            for (int i = 0; i < eegSampleBuffer.size() - minimumDetectionSize; i++)
            {
                int point = eegSampleBuffer.get(i);
                float slope = Math.abs(CalculateSlope(i, point, eegSampleBuffer.size() - 1, currentPoint));
                int delta = Math.abs(currentPoint - point);
                int timeLength = eegSampleBuffer.size() - 1 - i;

                if (timeLength >= minimumDetectionSize && timeLength <= maximumDetectionSize &&
                    slope <= maximumSlopeThreshold && slope >= minimumSlopeThreshold &&
                    delta >= minimumDeltaThreshold && delta <= maximumDeltaThreshold)
                {
                    isBlink = (int)(255 * (slope) / (maximumSlopeThreshold));
                    reportSlope = slope;
                    reportdelta = delta;
                    reporttimeLength = timeLength;
                    timeoutCounter = timeoutBlinkCounterLength;
                }

                if (slope > maximumSlopeThreshold || delta > maximumDeltaThreshold)
                {
                    timeoutCounter = timeoutSpikeCounterLength;
                    return 0;
                }
            }
        }

        int startPoint = eegSampleBuffer.size() >= minimumDetectionSize ? eegSampleBuffer.size()- minimumDetectionSize : 0;

        
        for (int i = startPoint; i < eegSampleBuffer.size(); i++)
        {
            int point = eegSampleBuffer.get( i);
            float slope = Math.abs(CalculateSlope(i, point, eegSampleBuffer.size()- 1, currentPoint));
            int delta = Math.abs(currentPoint - point);
            int timeLength = eegSampleBuffer.size()- 1 - i;

            // Disqualify as a blink if the slope is too high
            if (delta > maximumDeltaThreshold)
            {
                ClearEEGPoints();
    
                return 0;
            }
         }
        
        return isBlink;
    }
   
    public static void ClearEEGPoints()
    {
        timeoutCounter = 0;
        eegSampleBuffer.clear();
    }
  
}
