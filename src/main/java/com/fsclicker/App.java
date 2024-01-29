package com.fsclicker;

import javafx.application.Application;
import javafx.stage.Stage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.Border;

public class App extends Application {

    /**------------------------------------------------CONSTANTS--------------------------------------------*/

    final String LOCKED = "LOCKED";
    final int DISTANCE_TO_SWITCH_KM_TO_M = 5000;
    final int DISTANCE_DISPLAY_OBSTACLE_BOARD_STAGE2 = 600;
    final int DISTANCE_DISPLAY_OBSTACLE_BOARD_STAGE3 = 1200;
    final int DISTANCE_DISPLAY_OBSTACLE_BOARD_STAGE4 = 8000;
    final int DISTANCE_ENTER_OBSTACLE_ELIGIBILITY = 10;
    final int DISTANCE_TO_TRIGGER_FAST_OBSTACLE_RANGE_COUNTDOWN_SPEED = 10;
    final int TIME_UNTIL_PENALTY_STAGE2 = 600;
    final int TIME_UNTIL_PENALTY_STAGE3 = 720;
    final int TIME_UNTIL_PENALTY_STAGE4 = 1200;
    final int MAX_DISTANCE_TO_ADD_IF_STEVE_MOVES = 5000;
    final int MIN_DISTANCE_TO_ADD_IF_STEVE_MOVES = 2000;
    final int MIN_DISTANCE_TO_ADD_IF_DROPZONE_MOVES = 45000;
    final int MAX_DISTANCE_TO_ADD_IF_DROPZONE_MOVES = 70000;
    final double WITHIN_RANGE_OBST_DISTANCE_COUNT_SPEED = 0.1;
    final double FAST_OBSTACLE_WITHIN_RANGE_COUNTDOWN_SPEED = 0.008;
    final double WITHIN_RANGE_SUPER_FAST_DISTANCE_COUNT_SPEED = 0.002;
    final int DELAY_TO_REMOVE_OBST_PANEL_AFTER_OBSTACLE = 3;
    final int PRICE_TO_UNLOCK_TOW_TRUCK = 200;
    final int COST_INCREASE_PER_TOW_TRUCK = 50;
    final int TOW_TRUCK_ADDS_THIS_MANY_CLICKS_PER_SECOND = 10;
    final int PRICE_TO_UNLOCK_MECHANIC = 3500;
    final int PRICE_TO_UNLOCK_HIRED_HELP = 10;
    final int COST_INCREASE_PER_HIRED_HELP = 5;
    final double HIRED_HELP_ADDS_THIS_MANY_CLICKS_PER_SECOND = 0.1;
    final int CLICKS_TO_FIX_CAR = 500;
    final int NUMBER_OF_OBSTACLES_IN_STEVE_ARRAY_MINUS_1 = 19;
    final int NUMBER_OF_OBSTACLES_IN_AIRPORT_DASH_ARRAY_MINUS_1 = 8;
    final int NUMBER_OF_OBSTACLES_IN_FLIGHT_ARRAY_MINUS_1 = 5;
    final int MAX_NEW_OBSTACLE_DISTANCE_STG2 = 2000;
    final int MIN_NEW_OBSTACLE_DISTANCE_STG2 = 650;
    final int MAX_NEW_OBSTACLE_DISTANCE_STG3 = 10000;
    final int MIN_NEW_OBSTACLE_DISTANCE_STG3 = 5000;
    final int MAX_NEW_OBSTACLE_DISTANCE_STG4 = 18000;
    final int MIN_NEW_OBSTACLE_DISTANCE_STG4 = 12000;
    final int MIN_NEW_OBSTACLE_DISTANCE_WHILE_APPROACHING_STG3 = 800;
    final int MAX_NEW_TIMER_AMOUNT = 6;
    final int MIN_NEW_TIMER_AMOUNT = 4;
    final int COST_OF_FAILURE_FIRST_ITERATION_FACTOR = 12;
    final int COST_OF_FAILURE_FURTHER_ITERATIONS_FACTOR1 = 33;
    final int COST_OF_FAILURE_FURTHER_ITERATIONS_FACTOR2 = 10;
    final int DISTANCE_TO_STEVE = 30000;
    final int DISTANCE_TO_ESCAPE_PLANE = 50000;
    final int DISTANCE_TO_PARACHUTE_ZONE = 150000;
    final int COST_OF_FAILURE_STAGE3_START_VALUE = 1500;
    final int COST_OF_FAILURE_FOR_FAILING_APPROACH_OR_END_OF_STAGE_OBSTACLE = 10000;
    final int COST_OF_FAILURE_FOR_FAILING_DROPZONE_STAGE4 = 50000;
    final int COST_OF_FAILURE_FOR_FAILING_JUMPZONE_STAGE4 = 50000;
    final int MAX_NORMAL_SPEED_OF_CAR_MINUS_1 = 69;
    final int MAX_OVERDRIVE_SPEED_OF_CAR_MINUS_1 = 99;
    final int MAX_NO_OF_TURNING_CLICKS = 10;
    final int MIN_NO_OF_TURNING_CLICKS = 0;
    final int FIRST_OBSTACLE_STAGE2 = 25;
    final int FIRST_OBSTACLE_STAGE3 = 2500;
    final int FIRST_OBSTACLE_STAGE4 = 1000;
    final int NITROUS_COUNT = 30;
    final int OVERDRIVE_TOGGLE_SPEED = 70;
    final int MAX_SPEED_BOOST_CAN_BE_USED = 320;
    final int BOOST_AMOUNT_DELIVERED = 50;
    final int MAX_NUMBER_OF_SIMULTANEOUS_NITROS = 5;
    final int BOOST_DURATION = 8;
    final int TIME_TO_WAIT_TO_ADD_NITROS = 60;
    final int NITROS_TO_ADD_WHEN_BUTTON_CLICKED = 15;
    final int ACCEPTABLE_RANGE_TO_PARACHUTE = 300;
    final int MAX_SPEED_OF_JET_WITHOUT_OVERSPEEDING = 400; //300 from thrust + 100 from pitch down 60º
    final double JET_ENGINE_COEFFICIENT = 3;
    final double JET_ENGINE_COEFFICIENT_MODIFIER = 0.7;
    final double ACCELERATION_MODIFIER = 2;
    final int MAX_RIP_GEAR_OFF_DISTANCE = 4000;
    final int MIN_RIP_GEAR_OFF_DISTANCE = 2000;
    final int MIN_TAKEOFF_PITCH = 7;
    final int STALL_RECOVERY_COEFFICIENT = 70;
    final int ALTITUDE_COEFFICIENT = 4;
    final int PLANE_BREAK_OVERSPEED_VALUE = 7;
    final String[] obstacleNameArrayStg2 = {"Garage Gate", "Sheep Crossing", "Police Checkpoint", "Level Crossing", "Tractor Up Ahead", "Dog Running in Road", "Drunk Man in Road", "Fallen Rocks", "Broken Water Pipe", "Kids Playing in Road",
            "Turning Lorry Up Ahead", "Bin Wagon Spilled Trash", "Fallen Tree", "Road Works Up Ahead", "Bicycle Up Ahead", "Mate Wants A Race", "Road Rager Chasing You", "Minimum Speed Limit", "Out Accelerate A Sports Car", "Out Accelerate A Hatchback", "Steve"};
    final String[] obstacleNameArrayStg3 = {"Motorway Entry Road", "Slow Lorry Convoy", "Broken Down Van", "Pedestrian on Motorway", "Overpass Collapse", "Race a 1300cc Motorbike", "Jump The Ramp", "Max Power", "Steady She Goes", "Motorway Exit", "Airfield Entrance", "Escape Plane"};
    final String[] obstacleNameArrayStg4 = {"Takeoff", "Plane Inbound", "Storm", "Radar Failure", "Severe Turbulence", "Strange Phenomenon", "Drop Zone", "Parachute Zone"};
    final Integer[][] SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2 = {{0, 0, 0}, {2, 8, 1}, {1, 2, 0}, {0, 0, 0}, {3, 6, 1}, {2, 9, 1}, {4, 8, 1}, {3, 10, 1}, {6, 12, 1}, {2, 4, 1}, {8, 11, 1}, {6, 15, 1}, {3, 14, 1}, {0, 1, 1}, {3, 15, 1}, {50, 60, 0}, {45, 50, 0}, {30, 70, 0}, {65, 70, 0}, {60, 65, 0}, {0, 0, 0}};
    final Integer[][] SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3 = {{50, 70}, {35, 45}, {20, 30}, {10, 15}, {5, 10}, {100, 150}, {150, 200}, {320, 350}, {80, 120}, {30, 40}, {5, 10}, {0, 0}};
    final Integer[][] SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG4 = {{90, 100}, {230, 280}, {250, 300}, {270, 300}, {280, 290}, {120, 160}, {80, 90}, {90, 100}};
    final Integer[][] ALTITUDE_RANGE_REQUIRED_OBSTACLES_ARRAY_STG4 = {{0, 0}, {200, 250}, {400, 420}, {370, 390}, {280, 320}, {200, 250}, {80, 90}, {90, 100}};
    final double[][] PITCH_ARRAY = {{0, -5, 60, 100}, {1, -4, 45, 75}, {2, -3, 30, 50}, {3, -2, 20, 33}, {4, -1, 10, 17}, {5, 0, 0, 0}, {6, 1, 10, 0.889}, {7, 2, 20, 0.778}, {8, 3, 30, 0.667}, {9, 4, 45, 0.5}, {10, 5, 60, 0.333}}; // if climbing multiply max speed of thrust-level by factor, if descending, add factor on to max
    final double[][] THRUST_ARRAY = {{0, 0}, {1, 10}, {2, 20}, {3, 30}, {4, 40}, {5, 50}, {6, 60}, {7, 70}, {8, 80}, {9, 90}, {10, 100}, {11, 110}, {12, 120}, {13, 130}, {14, 140}, {15, 150}, {16, 160}, {17, 170}, {18, 180}, {19, 190}, {20, 200}, {21, 210}, {22, 220}, {23, 230}, {24, 240}, {25, 250}, {26, 260}, {27, 270}, {28, 280}, {29, 290}, {30, 300}};

    final double[][] ORIGINAL_THRUST_ARRAY = {{0, 0}, {1, 10}, {2, 20}, {3, 30}, {4, 40}, {5, 50}, {6, 60}, {7, 70}, {8, 80}, {9, 90}, {10, 100}, {11, 110}, {12, 120}, {13, 130}, {14, 140}, {15, 150}, {16, 160}, {17, 170}, {18, 180}, {19, 190}, {20, 200}, {21, 210}, {22, 220}, {23, 230}, {24, 240}, {25, 250}, {26, 260}, {27, 270}, {28, 280}, {29, 290}, {30, 300}};

    final double[][] THRUST_ARRAY_ADJUSTED_FOR_PITCH = {{0, 0}, {1, 10}, {2, 20}, {3, 30}, {4, 40}, {5, 50}, {6, 60}, {7, 70}, {8, 80}, {9, 90}, {10, 100}, {11, 110}, {12, 120}, {13, 130}, {14, 140}, {15, 150}, {16, 160}, {17, 170}, {18, 180}, {19, 190}, {20, 200}, {21, 210}, {22, 220}, {23, 230}, {24, 240}, {25, 250}, {26, 260}, {27, 270}, {28, 280}, {29, 290}, {30, 300}};

    final Integer[][] ALTITUDE_STALL_MAX_SPEED_RELATIONSHIP_ARRAY = {{80, 50, 0, 0}, {250, 60, 20, 20}, {300, 80, 40, 20}, {350, 110, 60, 20}, {400, 130, 80, 20}, {450, 150, 100, 20}}; //up to and including given altitude, stall speed shown, then adjuster for IAS
    final String[] DEGREES_ARRAY = {"-60º", "-45º", "-30º", "-20º", "-10º", "Level", "+10º", "+20º", "+30º", "+45º", "+60º"};

    /**------------------------------------------INITIAL VARIABLES------------------------------------------------ */
    String obstacleType, km, kmGoal, indAS, altString, hundredthsString;
    static BufferedImage pitch0;
    static BufferedImage pitch1;
    static BufferedImage pitch2;
    static BufferedImage pitch3;
    static BufferedImage pitch4;
    static BufferedImage pitch5_level;
    static BufferedImage pitch6;
    static BufferedImage pitch7;
    static BufferedImage pitch8;
    static BufferedImage pitch9;
    static BufferedImage pitch10;
    static BufferedImage jetImage;
    static BufferedImage mainDisplayImage;

    static ImageIcon mainImageDisplay;
    static ImageIcon pitchHUDDisplay;

    JPanel stallingWarningPanel = new JPanel();
    JPanel clickHere = new JPanel();
    JPanel priceLabelPanel = new JPanel();
    JPanel pricePanel = new JPanel();
    JPanel pitchInfo = new JPanel();
    JPanel speedLabel = new JPanel();
    JPanel flightLevelLabel = new JPanel();

    JButton mainImageButton = new JButton();
    JButton buttonAuxiliary = new JButton("NOS +" + NITROS_TO_ADD_WHEN_BUTTON_CLICKED);
    JButton button1 = new JButton(LOCKED);
    JButton button2 = new JButton(LOCKED);
    JButton button3 = new JButton(LOCKED);
    JButton button4 = new JButton(LOCKED);

    JLabel generalTimerElapsedValue, generalTimerElapsedLabel, metresTravelledLabel, clickCountLabel, perSecondLabelLabel, perSecondLabel, price, price1, price2, price3, price4, distanceToGoLabel, distanceToGoTitleLabel, whatIsNextObstacle, distanceToNextObstacleTitleLabel, distanceToNextObstacleLabel, obstacleConditionsTitle, obstacleConditions, timerObstacleTitle, timerObstacle, passFailObstacle, costOfFailure;
    JLabel stallingWarning = new JLabel();
    JLabel pitchDisplayContainer = new JLabel();
    JLabel indicatedAirspeed = new JLabel("");
    JLabel altitudeDisplayValue = new JLabel("");
    JLabel altitudeHundredthsDisplayValue = new JLabel("");

    Font font1, font2, font3, font4, fontHUD;

    ClickHandler cHandler = new ClickHandler();

    Timer timer, delayPanelAfterObstacleTimer, countDownToPassObstacleTimer, generalElapsedCounter;

    JTextArea messageText;

    MouseHandler mHandler = new MouseHandler();

    Border raisedBorder = BorderFactory.createRaisedBevelBorder();

    int stage, clickCount, timerSpeed, secondsElapsedDelayToRemoveObstaclePanel, autoClickerNumber, autoClickerPrice, towTruckNumber, towTruckPrice, mechanicPrice, button3ClickIteration, repairCounter, clicksLeftToFixCar, distanceToEndOfStageGoal;
    int randomGoalMovementModifier, driveFirstClickFlag, obstacleTarget, costOfFailureValue,timerObstacleValue, originalTimerObstacleValue, passObstacleFlag, speedRangePermitted, speedRangeActual, generalTimerElapsedSeconds, generalTimerSecondsToDisplay, generalTimerElapsedMinutes;
    int nitroBeingUsed, numberOfActiveNitros, nitrousBoostsRemainingCount, countDisplay, distanceToEndOfStageGoalAfterFail, requiredLeftClicks, requiredRightClicks, countTimesPassTimeUntilEndOStageGoal, leftClickCount, rightClickCount;
    int ripGearOffValue, thrustLevel, approachTrigger, timeUntilAuxiliaryButtonDisappears, nitroRechargeValue, nitroTicks1, nitroTicks2, nitroTicks3, nitroTicks4, nitroTicks5, originalGeneralTimerValue1, originalGeneralTimerValue2, originalGeneralTimerValue3, originalGeneralTimerValue4, originalGeneralTimerValue5;
    int originalOverSpeedTimerValue, recoverySpeed, randomSubHundredFeetValue, stallActive, altitudeZone, currentPitchToDisplay, currentPitch, altitudeRangeActual, altitudeRangePermitted;
    double newMaxSpeedAdjustedForPitch, altitudeAdjustedThrustArrayValue, altitudeProportion, altitude, indicatedAirspeedValue, altitudeGain, finalProportionPercentage, currentSpeedProportionOfMaxForThrustLevel, accelerationAmount, speedKnots, nextSuccessfulObstacleLeavesDistance, originalCPS, preNitroCPS1, preNitroCPS2, preNitroCPS3, preNitroCPS4, preNitroCPS5, clicksPerSecond, speedKmH, nextObstDistance;
    float repairCounterPercent;
    boolean overSpeedFlag, rippedGearOff, speedDegrading, hasTookOff, parachuteActive, dropZoneFlag, parachuteZoneFlag, landingGearUpFlag, BeginningOfADrivingStage, timerOn, delayObstaclePanelOn, button1Unlocked, button2Unlocked, mechanicTriggeredYet, button3Unlocked, driveUnlocked, carInMechanic, hasIncreasedSpeedFromZeroOnCurrentStageFlag, displayObstacleConditionsFlag, costOfFailureFirstIterationFlag;
    boolean failedObstacleWithinApproachOfEndOfStageGoal, approachingEndOfStageGoalFlag, checkTimeGoalMoveCount, startDelayTimerFlag, countDownToPassObstacleOn, startCountDownToPassObstacleFlag, wasPassingNowFailing, moreThanOneMinuteElapsedFlag;
    boolean altitudeZoneChangedFlag, planeBrokeFlag, stage4Start, approachFlag, adjustmentFlag, gameOverFlag, timerAuxiliaryButtonDisappearFlag, nitroSpeedUp1, nitroSpeedUp2, nitroSpeedUp3, nitroSpeedUp4, nitroSpeedUp5, nitro1, nitro2, nitro3, nitro4, nitro5, degradeNitro1, degradeNitro2, degradeNitro3, degradeNitro4, degradeNitro5, overDrive, atStartEngineScreen;
    boolean[] nitrosArray = {false, false, false, false, false};

    /**----------------------------------------------SET UP USER INTERFACE--------------------------------------------------*/
    
    public static void main(String[] args) {
        try {
            pitch0 = ImageIO.read(new File("src\\main\\resources\\com\\fsclicker\\pictures\\pitch0.png"));
            pitch1 = ImageIO.read(new File("src\\main\\resources\\com\\fsclicker\\pictures\\pitch1.png"));
            pitch2 = ImageIO.read(new File("src\\main\\resources\\com\\fsclicker\\pictures\\pitch2.png"));
            pitch3 = ImageIO.read(new File("src\\main\\resources\\com\\fsclicker\\pictures\\pitch3.png"));
            pitch4 = ImageIO.read(new File("src\\main\\resources\\com\\fsclicker\\pictures\\pitch4.png"));
            pitch5_level = ImageIO.read(new File("src\\main\\resources\\com\\fsclicker\\pictures\\pitch5_level.png"));
            pitch6 = ImageIO.read(new File("src\\main\\resources\\com\\fsclicker\\pictures\\pitch6.png"));
            pitch7 = ImageIO.read(new File("src\\main\\resources\\com\\fsclicker\\pictures\\pitch7.png"));
            pitch8 = ImageIO.read(new File("src\\main\\resources\\com\\fsclicker\\pictures\\pitch8.png"));
            pitch9 = ImageIO.read(new File("src\\main\\resources\\com\\fsclicker\\pictures\\pitch9.png"));
            pitch10 = ImageIO.read(new File("src\\main\\resources\\com\\fsclicker\\pictures\\pitch10.png"));
            jetImage = ImageIO.read(new File("src\\main\\resources\\com\\fsclicker\\pictures\\jetImage.png"));
            mainDisplayImage = ImageIO.read(new File("src\\main\\resources\\com\\fsclicker\\pictures\\carImage.png"));
        } catch (Exception e) {
            System.out.println("Cannot load resources: " + e);
        }

        mainImageDisplay = new ImageIcon(mainDisplayImage);
        pitchHUDDisplay = new ImageIcon(pitch5_level);

        // launch();
        System.out.println("Starting app...");
        new App();
    }

    public App() {
        System.out.println("Starting module...");
        setInitialVariables(false); //initial setup
        createFont();
        createUI(mainDisplayImage);
    }

    @Override
    public void start(Stage stage) {}

    public void createFont(){
        font1 = new Font("Arial", Font.PLAIN, 32);
        font2 = new Font("Arial", Font.PLAIN, 15);
        font3 = new Font("Arial", Font.BOLD, 27);
        font4 = new Font("Arial", Font.PLAIN, 16);
        fontHUD = new Font("Consolas", Font.PLAIN, 18);
    }
    public void createUI(BufferedImage initialImage) {

        JFrame window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);

        stallingWarningPanel.setBounds(20,5,750,40);
        stallingWarningPanel.setOpaque(false);
        stallingWarningPanel.setVisible(false);
        stallingWarningPanel.setLayout(new GridLayout(1,1));
        window.add(stallingWarningPanel);

        stallingWarning.setForeground(Color.red);
        stallingWarning.setFont(font4);
        stallingWarning.setHorizontalTextPosition(JLabel.LEFT);
        stallingWarningPanel.add(stallingWarning);

        speedLabel.setBounds(377,157,60,20);
        speedLabel.setOpaque(false);
        speedLabel.setVisible(false);
        speedLabel.setLayout(new GridLayout(1,1));
        window.add(speedLabel);

        indicatedAirspeed.setForeground(Color.decode("#58b200"));
        indicatedAirspeed.setFont(fontHUD);
        indicatedAirspeed.setHorizontalTextPosition(JLabel.LEFT);
        speedLabel.add(indicatedAirspeed);

        flightLevelLabel.setBounds(377,175,60,20);
        flightLevelLabel.setOpaque(false);
        flightLevelLabel.setVisible(false);
        flightLevelLabel.setLayout(new GridLayout(1,2));
        window.add(flightLevelLabel);

        altitudeDisplayValue.setForeground(Color.decode("#58b200"));
        altitudeDisplayValue.setFont(fontHUD);
        altitudeDisplayValue.setHorizontalAlignment(JLabel.LEFT);
        flightLevelLabel.add(altitudeDisplayValue);

        altitudeHundredthsDisplayValue.setForeground(Color.decode("#58b200"));
        altitudeHundredthsDisplayValue.setFont(fontHUD);
        altitudeHundredthsDisplayValue.setHorizontalAlignment(JLabel.LEFT);
        flightLevelLabel.add(altitudeHundredthsDisplayValue);

        pitchInfo.setBounds(370, 146, 107, 255);
        pitchInfo.setOpaque(false);
        window.add(pitchInfo);
        pitchInfo.setVisible(false);

        pitchHUDDisplay.setImage(pitch5_level);

        pitchDisplayContainer.setOpaque(false);
        pitchDisplayContainer.setBorder(null);
        pitchDisplayContainer.setIcon(pitchHUDDisplay);
        pitchInfo.add(pitchDisplayContainer);

        clickHere.setBounds(100,200,199,200);
        clickHere.setOpaque(false);
        window.add(clickHere);

        mainImageDisplay.setImage(initialImage);

        mainImageButton.setOpaque(false);
        mainImageButton.setBackground(Color.black);
        mainImageButton.setBorder(null);
        mainImageButton.setIcon(mainImageDisplay);
        mainImageButton.addActionListener(cHandler);
        mainImageButton.setActionCommand("AdvanceStage"); // pushCar real, AdvanceStage DEBUG
        clickHere.add(mainImageButton);

        JPanel addNitros = new JPanel();
        addNitros.setBounds(680,25,100,100);
        addNitros.setOpaque(false);
        addNitros.setLayout(new GridLayout(1,1));
        window.add(addNitros);

        buttonAuxiliary.setFont(font2);
        buttonAuxiliary.setFocusPainted(false);
        buttonAuxiliary.addActionListener(cHandler);
        buttonAuxiliary.setActionCommand("AddNitros");
        buttonAuxiliary.addMouseListener(mHandler);
        buttonAuxiliary.setVisible(false);
        addNitros.add(buttonAuxiliary);

        JPanel clickCounter = new JPanel();
        clickCounter.setBounds(100,50,520,150);
        clickCounter.setOpaque(false);
        clickCounter.setLayout(new GridLayout(6,1));
        window.add(clickCounter);

        generalTimerElapsedLabel = new JLabel("");
        generalTimerElapsedLabel.setForeground(Color.yellow);
        generalTimerElapsedLabel.setFont(font2);
        clickCounter.add(generalTimerElapsedLabel);

        generalTimerElapsedValue = new JLabel("");
        generalTimerElapsedValue.setForeground(Color.white);
        generalTimerElapsedValue.setFont(font1);
        clickCounter.add(generalTimerElapsedValue);

        metresTravelledLabel = new JLabel("Distance travelled:");
        metresTravelledLabel.setForeground(Color.yellow);
        metresTravelledLabel.setFont(font2);
        clickCounter.add(metresTravelledLabel);

        clickCountLabel = new JLabel(clickCount+"m");
        clickCountLabel.setForeground(Color.white);
        clickCountLabel.setFont(font1);
        clickCounter.add(clickCountLabel);

        perSecondLabelLabel = new JLabel();
        perSecondLabelLabel.setForeground(Color.yellow);
        perSecondLabelLabel.setFont(font2);
        clickCounter.add(perSecondLabelLabel);

        perSecondLabel = new JLabel();
        perSecondLabel.setForeground(Color.white);
        perSecondLabel.setFont(font2);
        clickCounter.add(perSecondLabel);

        JPanel obstacleInfoPanel = new JPanel();
        obstacleInfoPanel.setBounds(500,401,250,150);
        obstacleInfoPanel.setOpaque(false);
        obstacleInfoPanel.setLayout(new GridLayout(6,1));
        window.add(obstacleInfoPanel);

        obstacleConditionsTitle = new JLabel("");
        obstacleConditionsTitle.setForeground(Color.yellow);
        obstacleConditionsTitle.setFont(font2);
        obstacleInfoPanel.add(obstacleConditionsTitle);

        obstacleConditions = new JLabel("");
        obstacleConditions.setForeground(Color.white);
        obstacleConditions.setFont(font2);
        obstacleInfoPanel.add(obstacleConditions);

        timerObstacleTitle = new JLabel("");
        timerObstacleTitle.setForeground(Color.yellow);
        timerObstacleTitle.setFont(font2);
        obstacleInfoPanel.add(timerObstacleTitle);

        timerObstacle = new JLabel("");
        timerObstacle.setForeground(Color.white);
        timerObstacle.setFont(font2);
        obstacleInfoPanel.add(timerObstacle);

        costOfFailure = new JLabel("");
        costOfFailure.setForeground(Color.red);
        costOfFailure.setFont(font2);
        obstacleInfoPanel.add(costOfFailure);

        passFailObstacle = new JLabel("");
        passFailObstacle.setForeground(Color.green);
        passFailObstacle.setFont(font1);
        obstacleInfoPanel.add(passFailObstacle);

        priceLabelPanel.setBounds(390,100,100,50);
        priceLabelPanel.setOpaque(false);
        priceLabelPanel.setLayout(new GridLayout(1,1));
        window.add(priceLabelPanel);

        price = new JLabel("Price");
        price.setHorizontalAlignment(JLabel.RIGHT);
        price.setForeground(Color.white);
        price.setFont(font1);
        priceLabelPanel.add(price);

        pricePanel.setBounds(390,150,100,250);
        pricePanel.setOpaque(false);
        pricePanel.setLayout(new GridLayout(4,1));
        window.add(pricePanel);

        price1 = new JLabel("10m");
        price1.setHorizontalAlignment(JLabel.LEFT);
        price1.setForeground(Color.white);
        price1.setFont(font1);
        pricePanel.add(price1);

        price2 = new JLabel();
        price2.setHorizontalAlignment(JLabel.RIGHT);
        price2.setForeground(Color.white);
        price2.setFont(font1);
        pricePanel.add(price2);

        price3 = new JLabel();
        price3.setHorizontalAlignment(JLabel.RIGHT);
        price3.setForeground(Color.white);
        price3.setFont(font1);
        pricePanel.add(price3);

        price4 = new JLabel();
        price4.setHorizontalAlignment(JLabel.RIGHT);
        price4.setForeground(Color.white);
        price4.setFont(font1);
        pricePanel.add(price4);

        JPanel itemPanel = new JPanel();
        itemPanel.setBounds(500,150,280,250);
        itemPanel.setOpaque(false);
        itemPanel.setLayout(new GridLayout(4,1));
        window.add(itemPanel);

        button1.setFont(font1);
        button1.setFocusPainted(false);
        button1.addActionListener(cHandler);
        button1.setActionCommand("Hired Help");
        button1.addMouseListener(mHandler);
        itemPanel.add(button1);

        button2.setFont(font1);
        button2.setFocusPainted(false);
        button2.addActionListener(cHandler);
        button2.setActionCommand("Tow Truck");
        button2.addMouseListener(mHandler);
        itemPanel.add(button2);

        button3.setFont(font1);
        button3.setFocusPainted(false);
        button3.addActionListener(cHandler);
        button3.setActionCommand("Mechanic");
        button3.addMouseListener(mHandler);
        itemPanel.add(button3);

        button4.setFont(font1);
        button4.setFocusPainted(false);
        button4.addActionListener(cHandler);
        button4.addMouseListener(mHandler);
        itemPanel.add(button4);

        JPanel messagePanel = new JPanel();
        messagePanel.setBounds(530,25,140,150);
        messagePanel.setOpaque(false);
        window.add(messagePanel);

        messageText = new JTextArea();
        messageText.setBounds(530, 25, 140, 150);
        messageText.setForeground(Color.yellow);
        messageText.setOpaque(false);
        messageText.setFont(font2);
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setEditable(false);
        messagePanel.add(messageText);

        JPanel bottomDistanceInfoPanel = new JPanel();
        bottomDistanceInfoPanel.setBounds(100,415,300,130);
        bottomDistanceInfoPanel.setOpaque(false);
        bottomDistanceInfoPanel.setLayout(new GridLayout(5,1));
        window.add(bottomDistanceInfoPanel);

        distanceToGoTitleLabel = new JLabel("");
        distanceToGoTitleLabel.setForeground(Color.yellow);
        distanceToGoTitleLabel.setFont(font2);
        bottomDistanceInfoPanel.add(distanceToGoTitleLabel);

        distanceToGoLabel = new JLabel("");
        distanceToGoLabel.setForeground(Color.white);
        distanceToGoLabel.setFont(font1);
        bottomDistanceInfoPanel.add(distanceToGoLabel);

        distanceToNextObstacleTitleLabel = new JLabel("");
        distanceToNextObstacleTitleLabel.setForeground(Color.yellow);
        distanceToNextObstacleTitleLabel.setFont(font2);
        bottomDistanceInfoPanel.add(distanceToNextObstacleTitleLabel);

        distanceToNextObstacleLabel = new JLabel("");
        distanceToNextObstacleLabel.setForeground(Color.white);
        distanceToNextObstacleLabel.setFont(font1);
        bottomDistanceInfoPanel.add(distanceToNextObstacleLabel);

        whatIsNextObstacle = new JLabel("");
        whatIsNextObstacle.setForeground(Color.white);
        whatIsNextObstacle.setFont(font2);
        bottomDistanceInfoPanel.add(whatIsNextObstacle);

        window.setVisible(true);
    }

    /**----------------------------------------------MAIN GAME CODE------------------------------------------------------------*/

    public void setGeneralTimer() {
        generalElapsedCounter = new Timer(1000, e -> {
            generalTimerElapsedSeconds++;
            generalTimerSecondsToDisplay++;

            if (stage == 3 && generalTimerElapsedSeconds - nitroRechargeValue == TIME_TO_WAIT_TO_ADD_NITROS) {
                buttonAuxiliary.setVisible(true);
            }
            if (timerAuxiliaryButtonDisappearFlag && (generalTimerElapsedSeconds - timeUntilAuxiliaryButtonDisappears == 1)) {
                buttonAuxiliary.setVisible(false);
                timerAuxiliaryButtonDisappearFlag = false;
                messageText.setText(null);
            }
        });
    }

    public void setDelayPanelAfterObstacleTimer() {
        delayPanelAfterObstacleTimer = new Timer(1000, e -> secondsElapsedDelayToRemoveObstaclePanel++);
    }

    private void setCountDownToPassObstacleTimer() {
        countDownToPassObstacleTimer = new Timer(1000, e -> {
            timerObstacleValue--;
            if (timerObstacleValue >=0 && (stage == 2 || stage == 3)) {
                timerObstacle.setText(timerObstacleValue + "s");
                if (!approachingEndOfStageGoalFlag) {
                    distanceToNextObstacleLabel.setText((int) nextObstDistance + "m");
                }
            }
            if (timerObstacleValue <=0 && countDownToPassObstacleOn && stage != 4) {
                countDownToPassObstacleTimer.stop();
                passObstacleFlag = 1;
                countDownToPassObstacleOn = false;
                countDownToPassObstacleTimerUpdate();
            }
            if (nextObstDistance <= 0 && (speedRangeActual > speedRangePermitted || speedRangeActual < 0) && stage != 4) {
                passObstacleFlag = 2;
                countDownToPassObstacleTimerUpdate();
            }
        });
    }
    public void setTimer() {
        timer = new Timer(timerSpeed, e -> {
            clickCount++;
            if (stage == 4 && hasTookOff) {
                calculateAltitude();
                if (altitudeZoneChangedFlag){
                    adjustSpeedForAltitudeZone();
                }
                adjustSpeedForPitch();
                if (altitude <= 0) {
                    gameOver();
                }
                if (stallActive == 0) {
                    stallCheck();
                }
                if (stallActive == 1) {
                    stallSetup();
                }
                if (stallActive == 2) {
                    recoverStallCheck();
                }
            }
            if (stage == 4) {
                randomSubHundredFeetValue = (int) (Math.random() * 99);
                hundredthsString = String.format("%02d", randomSubHundredFeetValue);
                if (hasTookOff && currentPitch != 5) {
                    altString = String.format("%03d", (int) altitude);
                    altitudeDisplayValue.setText(altString);
                    altitudeHundredthsDisplayValue.setText(hundredthsString + "");
                } else {
                    altitudeHundredthsDisplayValue.setText("00");
                }

                switch (altitudeZone) {
                    case 0:
                        indicatedAirspeedValue = clicksPerSecond; // sraheel this is when the plane is down near the ground and clicksPerSecond should not be changed as it is the base speed before modifying.  Modifying is done in indicatedAirSpeedValue
                        break;
                    case 1, 2, 3, 4, 5: // sraheel this is where the speed is altered on each tick by the altitudeProportion calculated
                        while (indicatedAirspeedValue > (altitudeAdjustedThrustArrayValue / 100)) {
                            indicatedAirspeedValue = indicatedAirspeedValue - (ALTITUDE_STALL_MAX_SPEED_RELATIONSHIP_ARRAY[altitudeZone][2] * altitudeProportion);
                        }
                        break;
                }

                indAS = String.format("%03d", (int) indicatedAirspeedValue);
                indicatedAirspeed.setText(indAS + "IAS");
                if (indicatedAirspeedValue > (MAX_SPEED_OF_JET_WITHOUT_OVERSPEEDING) && !overSpeedFlag) {
                    stallingWarningPanel.setVisible(true);
                    stallingWarning.setText("OVERSPEEDING! REDUCE TO LESS THAN 400m/s IN LESS THAN " + (PLANE_BREAK_OVERSPEED_VALUE) + "s!");
                    overSpeedFlag = true;
                    originalOverSpeedTimerValue = generalTimerElapsedSeconds;
                }
                if (overSpeedFlag && indicatedAirspeedValue > MAX_SPEED_OF_JET_WITHOUT_OVERSPEEDING) {
                    stallingWarning.setText("OVERSPEEDING! REDUCE TO LESS THAN 400m/s IN LESS THAN " + (PLANE_BREAK_OVERSPEED_VALUE - (generalTimerElapsedSeconds - originalOverSpeedTimerValue)) + "s!");
                }
                if (indicatedAirspeedValue <= MAX_SPEED_OF_JET_WITHOUT_OVERSPEEDING && overSpeedFlag) {
                    overSpeedFlag = false;
                    if (stallActive == 2) {
                        stallingWarning.setText("STALLING! TO RECOVER, ACCELERATE TO AT LEAST " + (ALTITUDE_STALL_MAX_SPEED_RELATIONSHIP_ARRAY[altitudeZone][1] + STALL_RECOVERY_COEFFICIENT) + "IAS.");
                    } else {
                        stallingWarningPanel.setVisible(false);
                    }
                } else if (overSpeedFlag && generalTimerElapsedSeconds - originalOverSpeedTimerValue >= PLANE_BREAK_OVERSPEED_VALUE) {
                    planeBrokeFlag = true;
                    gameOver();
                }
                if (speedDegrading) {
                    currentSpeedProportionOfMaxForThrustLevel = (clicksPerSecond - THRUST_ARRAY[thrustLevel][1]);
                }
                    clicksPerSecond = (clicksPerSecond + accelerationAmount);
                if (!speedDegrading && clicksPerSecond > THRUST_ARRAY[thrustLevel][1]) {
                    clicksPerSecond = THRUST_ARRAY[thrustLevel][1];
                } else if (!speedDegrading && clicksPerSecond > (THRUST_ARRAY[thrustLevel][1] - 0.05)) {
                    clicksPerSecond = THRUST_ARRAY[thrustLevel][1];
                } else if (speedDegrading && clicksPerSecond <= THRUST_ARRAY[thrustLevel][1]) {
                    accelerationAmount = 0;
                    speedDegrading = false;
                    clicksPerSecond = THRUST_ARRAY[thrustLevel][1];
                }
                timerUpdate(nitroBeingUsed, thrustLevel);
            }
            if ((nitroSpeedUp1 || nitroSpeedUp2 || nitroSpeedUp3 || nitroSpeedUp4 || nitroSpeedUp5) && stage == 3) {
                timerUpdate(nitroBeingUsed, thrustLevel);
            }
            if (stage == 3 && nitro1 && !degradeNitro1 && (generalTimerElapsedSeconds - originalGeneralTimerValue1 >= BOOST_DURATION)) {
                System.out.println("time to degrade nitro1 down");
                degradeNitro1 = true;
                timerUpdate(nitroBeingUsed, thrustLevel);
            }
            if (stage == 3 && nitro2 && !degradeNitro2 && (generalTimerElapsedSeconds - originalGeneralTimerValue2 >= BOOST_DURATION)) {
                System.out.println("time to degrade nitro2 down");
                degradeNitro2 = true;
                timerUpdate(nitroBeingUsed, thrustLevel);
            }
            if (stage == 3 && nitro3 && !degradeNitro3 && (generalTimerElapsedSeconds - originalGeneralTimerValue3 >= BOOST_DURATION)) {
                System.out.println("time to degrade nitro3 down");
                degradeNitro3 = true;
                timerUpdate(nitroBeingUsed, thrustLevel);
            }
            if (stage == 3 && nitro4 && !degradeNitro4 && (generalTimerElapsedSeconds - originalGeneralTimerValue4 >= BOOST_DURATION)) {
                System.out.println("time to degrade nitro4 down");
                degradeNitro4 = true;
                timerUpdate(nitroBeingUsed, thrustLevel);
            }
            if (stage == 3 && nitro5 && !degradeNitro5 && (generalTimerElapsedSeconds - originalGeneralTimerValue5 >= BOOST_DURATION)) {
                System.out.println("time to degrade nitro5 down");
                degradeNitro5 = true;
                timerUpdate(nitroBeingUsed, thrustLevel);
            }
            if (stage == 3 && nitro1) {
                if (nitroTicks1 >= BOOST_AMOUNT_DELIVERED && degradeNitro1) {
                    timerUpdate(nitroBeingUsed, thrustLevel);
                }
                if (nitroTicks1 < BOOST_AMOUNT_DELIVERED && degradeNitro1) {
                    timerUpdate(nitroBeingUsed, thrustLevel);
                }
            }
            if (stage == 3 && nitro2) {
                if (nitroTicks2 >= BOOST_AMOUNT_DELIVERED && degradeNitro2) {
                    timerUpdate(nitroBeingUsed, thrustLevel);
                }
                if (nitroTicks2 < BOOST_AMOUNT_DELIVERED && degradeNitro2) {
                    timerUpdate(nitroBeingUsed, thrustLevel);
                }
            }
            if (stage == 3 && nitro3) {
                if (nitroTicks3 >= BOOST_AMOUNT_DELIVERED && degradeNitro3) {
                    timerUpdate(nitroBeingUsed, thrustLevel);
                }
                if (nitroTicks3 < BOOST_AMOUNT_DELIVERED && degradeNitro3) {
                    timerUpdate(nitroBeingUsed, thrustLevel);
                }
            }
            if (stage == 3 && nitro4) {
                if (nitroTicks4 >= BOOST_AMOUNT_DELIVERED && degradeNitro4) {
                    timerUpdate(nitroBeingUsed, thrustLevel);
                }
                if (nitroTicks4 < BOOST_AMOUNT_DELIVERED && degradeNitro4) {
                    timerUpdate(nitroBeingUsed, thrustLevel);
                }
            }
            if (stage == 3 && nitro5) {
                if (nitroTicks5 >= BOOST_AMOUNT_DELIVERED && degradeNitro5) {
                    timerUpdate(nitroBeingUsed, thrustLevel);
                }
                if (nitroTicks5 < BOOST_AMOUNT_DELIVERED && degradeNitro5) {
                    timerUpdate(nitroBeingUsed, thrustLevel);
                }
            }
            if (stage == 2 || stage == 3 || stage == 4) {
                if (stage == 3 && overDrive && clicksPerSecond < OVERDRIVE_TOGGLE_SPEED) {
                    overDrive = false;
                    button3.setForeground(Color.black);
                    button3.setText("Overdrive: OFF");
                }
                distanceToEndOfStageGoal--;
                double cCountAsDouble = clickCount;
                double distanceToEndOfStageGoalAsDouble = distanceToEndOfStageGoal;
                DecimalFormat num = new DecimalFormat("0.00");
                num.setRoundingMode(RoundingMode.FLOOR);
                km = num.format(cCountAsDouble/1000);
                kmGoal = num.format(distanceToEndOfStageGoalAsDouble/1000);
                if (clickCount > DISTANCE_TO_SWITCH_KM_TO_M) {
                    clickCountLabel.setText(km + "km");
                } else {
                    clickCountLabel.setText(clickCount + "m");
                }
                if (distanceToEndOfStageGoal >= DISTANCE_TO_SWITCH_KM_TO_M) {
                    distanceToGoLabel.setText(kmGoal + "km");
                } else {
                    distanceToGoLabel.setText(distanceToEndOfStageGoal + "m");
                }
                if (!approachingEndOfStageGoalFlag) {
                    if (stage == 2) {
                        generalTimerElapsedLabel.setText("Steve Moves Every " + (TIME_UNTIL_PENALTY_STAGE2 / 60) + " Minutes:");
                    } else if (stage == 3) {
                        generalTimerElapsedLabel.setText("Escape Plane Leaves In " + (TIME_UNTIL_PENALTY_STAGE3 / 60) + " min!");
                    } else if (stage == 4) {
                        generalTimerElapsedLabel.setText("DropZone Secure For " + (TIME_UNTIL_PENALTY_STAGE4 / 60) + " min!");
                    }
                }
                if (generalTimerElapsedSeconds >= 60 && generalTimerSecondsToDisplay >= 60) {
                    generalTimerElapsedMinutes++;
                    generalTimerSecondsToDisplay = 0;
                    moreThanOneMinuteElapsedFlag = true;
                }
                if (!moreThanOneMinuteElapsedFlag && generalTimerElapsedSeconds < 60 && !gameOverFlag) {
                    if (generalTimerElapsedSeconds < 10 && !approachingEndOfStageGoalFlag) {
                        generalTimerElapsedValue.setText("0:0" + generalTimerSecondsToDisplay);
                    } else {
                        if(!approachingEndOfStageGoalFlag) {
                            generalTimerElapsedValue.setText("0:" + generalTimerSecondsToDisplay);
                        }
                    }
                } else {
                    if (generalTimerSecondsToDisplay < 10 && !approachingEndOfStageGoalFlag && !gameOverFlag) {
                        generalTimerElapsedValue.setText(generalTimerElapsedMinutes + ":0" + generalTimerSecondsToDisplay);
                    } else {
                        if (!approachingEndOfStageGoalFlag && !gameOverFlag) {
                            generalTimerElapsedValue.setText(generalTimerElapsedMinutes + ":" + generalTimerSecondsToDisplay);
                        }
                    }
                }
                if (stage == 2 || stage == 4) {
                    if (stage == 2) {
                        if (generalTimerElapsedSeconds % TIME_UNTIL_PENALTY_STAGE2 == 0 && !checkTimeGoalMoveCount) {
                            checkTimeGoalMoveCount = true;
                            randomGoalMovementModifier = (int) ((Math.random() * (MAX_DISTANCE_TO_ADD_IF_STEVE_MOVES - MIN_DISTANCE_TO_ADD_IF_STEVE_MOVES)) + MIN_DISTANCE_TO_ADD_IF_STEVE_MOVES);
                            distanceToEndOfStageGoal = distanceToEndOfStageGoal + randomGoalMovementModifier;
                            if (distanceToEndOfStageGoal > DISTANCE_TO_SWITCH_KM_TO_M) {
                                distanceToGoLabel.setText(kmGoal + "km");
                            } else {
                                distanceToGoLabel.setText(distanceToEndOfStageGoal + "m");
                            }
                        }
                        if (generalTimerElapsedSeconds >= (TIME_UNTIL_PENALTY_STAGE2 * countTimesPassTimeUntilEndOStageGoal) - 30 && generalTimerElapsedSeconds < (TIME_UNTIL_PENALTY_STAGE2 * countTimesPassTimeUntilEndOStageGoal)) {
                            generalTimerElapsedValue.setForeground(Color.red);
                            checkTimeGoalMoveCount = false;
                        } else if (generalTimerElapsedSeconds >= TIME_UNTIL_PENALTY_STAGE2 * countTimesPassTimeUntilEndOStageGoal && !approachingEndOfStageGoalFlag) {
                            generalTimerElapsedValue.setForeground(Color.white);
                            generalTimerElapsedValue.setText("0:00");
                            countTimesPassTimeUntilEndOStageGoal++;
                            generalTimerElapsedMinutes = 0;
                            generalTimerSecondsToDisplay = 0;
                        }
                    } else {
                        if (generalTimerElapsedSeconds % TIME_UNTIL_PENALTY_STAGE4 == 0 && !checkTimeGoalMoveCount) {
                            checkTimeGoalMoveCount = true;
                            randomGoalMovementModifier = (int) ((Math.random() * (MAX_DISTANCE_TO_ADD_IF_DROPZONE_MOVES - MIN_DISTANCE_TO_ADD_IF_DROPZONE_MOVES)) + MIN_DISTANCE_TO_ADD_IF_DROPZONE_MOVES);
                            distanceToEndOfStageGoal = distanceToEndOfStageGoal + randomGoalMovementModifier;
                            if (distanceToEndOfStageGoal > DISTANCE_TO_SWITCH_KM_TO_M) {
                                distanceToGoLabel.setText(kmGoal + "km");
                            } else {
                                distanceToGoLabel.setText(distanceToEndOfStageGoal + "m");
                            }
                        }
                        if (generalTimerElapsedSeconds >= (TIME_UNTIL_PENALTY_STAGE4 * countTimesPassTimeUntilEndOStageGoal) - 30 && generalTimerElapsedSeconds < (TIME_UNTIL_PENALTY_STAGE4 * countTimesPassTimeUntilEndOStageGoal)) {
                            generalTimerElapsedValue.setForeground(Color.red);
                            checkTimeGoalMoveCount = false;
                        } else if (generalTimerElapsedSeconds >= TIME_UNTIL_PENALTY_STAGE4 * countTimesPassTimeUntilEndOStageGoal && !approachingEndOfStageGoalFlag) {
                            generalTimerElapsedValue.setForeground(Color.white);
                            generalTimerElapsedValue.setText("0:00");
                            countTimesPassTimeUntilEndOStageGoal++;
                            generalTimerElapsedMinutes = 0;
                            generalTimerSecondsToDisplay = 0;
                        }
                    }
                } else if (stage == 3 && generalTimerElapsedSeconds >= TIME_UNTIL_PENALTY_STAGE3) {
                    gameOver();
                }
                if (stage == 3 && generalTimerElapsedSeconds >= (TIME_UNTIL_PENALTY_STAGE3 * countTimesPassTimeUntilEndOStageGoal) - 30 && generalTimerElapsedSeconds < (TIME_UNTIL_PENALTY_STAGE3 * countTimesPassTimeUntilEndOStageGoal)) {
                    generalTimerElapsedValue.setForeground(Color.red);
                }
                if (!countDownToPassObstacleOn && !gameOverFlag) {
                    nextObstDistance--;
                } else {
                    if (stage != 4 && nextObstDistance < DISTANCE_TO_TRIGGER_FAST_OBSTACLE_RANGE_COUNTDOWN_SPEED) {
                        nextObstDistance = nextObstDistance - FAST_OBSTACLE_WITHIN_RANGE_COUNTDOWN_SPEED;
                    }
                    else {
                        if (stage == 4) {
                            nextObstDistance = nextObstDistance - WITHIN_RANGE_SUPER_FAST_DISTANCE_COUNT_SPEED;
                        } else {
                            nextObstDistance = nextObstDistance - WITHIN_RANGE_OBST_DISTANCE_COUNT_SPEED;
                        }
                    }
                }
                if (!gameOverFlag && secondsElapsedDelayToRemoveObstaclePanel > 2) {
                    passFailObstacle.setText("");
                }
                if (!approachingEndOfStageGoalFlag) {
                    distanceToNextObstacleLabel.setText(((int) nextObstDistance) + "m");
                }
                if(stage == 2 && nextObstDistance < DISTANCE_DISPLAY_OBSTACLE_BOARD_STAGE2 && nextObstDistance >= 3) {
                    displayObstacleConditionsFlag = true;
                    countDisplay++;
                    displayObstaclePassConditions(countDisplay);
                } else if (stage == 3 && nextObstDistance < DISTANCE_DISPLAY_OBSTACLE_BOARD_STAGE3 && nextObstDistance >= 3) {
                    displayObstacleConditionsFlag = true;
                    countDisplay++;
                    displayObstaclePassConditions(countDisplay);
                } else if (stage == 4 && nextObstDistance < DISTANCE_DISPLAY_OBSTACLE_BOARD_STAGE4 && nextObstDistance >= 3) {
                    displayObstacleConditionsFlag = true;
                    countDisplay++;
                    displayObstaclePassConditions(countDisplay);
                }
                if (stage != 4 && nextObstDistance < DISTANCE_ENTER_OBSTACLE_ELIGIBILITY && !countDownToPassObstacleOn && nextObstDistance >= 0 && (speedRangeActual <= speedRangePermitted && speedRangeActual >= 0)) {
                    startCountDownToPassObstacleFlag = true;
                    setCountDownToPassObstacleTimer();
                    countDownToPassObstacleTimerUpdate();
                }
                if ((stage == 2 || stage == 3) && (speedRangeActual > speedRangePermitted || speedRangeActual < 0) && (countDownToPassObstacleOn || nextObstDistance <= 0)) {
                    if (nextObstDistance <= 0) {
                        passObstacleFlag = 2;
                    }
                    countDownToPassObstacleTimerUpdate();
                }
                if (stage == 4 && (speedRangeActual > speedRangePermitted || speedRangeActual < 0) && (altitudeRangeActual > altitudeRangePermitted || altitudeRangeActual < 0) && (nextObstDistance <= 0)) {
                    passObstacleFlag = 2;
                    obstacleFailed();
                }
                if (stage == 4 && obstacleTarget == 0 && currentPitch >= MIN_TAKEOFF_PITCH && clicksPerSecond >= SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG4[0][0] && clicksPerSecond <= SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG4[0][1]) {
                    passObstacleFlag = 1;
                    obstaclePassed();
                }
                if (stage == 4 && nextObstDistance < DISTANCE_ENTER_OBSTACLE_ELIGIBILITY && nextObstDistance >= 0 && (speedRangeActual <= speedRangePermitted && speedRangeActual >= 0)) { // add extra stage 4 conditions if necessary when testing done
                    passObstacleFlag = 1;
                    obstaclePassed();
                }
                if (stage == 4 && nextObstDistance < DISTANCE_ENTER_OBSTACLE_ELIGIBILITY && !hasTookOff && currentPitch < MIN_TAKEOFF_PITCH ) {
                    passObstacleFlag = 2;
                    obstacleFailed();
                }
                if (!gameOverFlag && !displayObstacleConditionsFlag) {
                    countDisplay = 0;
                    obstacleConditionsTitle.setText("");
                    obstacleConditions.setText("");
                    timerObstacleTitle.setText("");
                    timerObstacle.setText("");
                    costOfFailure.setText("");
                    passFailObstacle.setText("");
                }
                if(secondsElapsedDelayToRemoveObstaclePanel >= DELAY_TO_REMOVE_OBST_PANEL_AFTER_OBSTACLE) {
                    displayObstacleConditionsFlag = false;
                    delayPanelAfterObstacleTimer.stop();
                    secondsElapsedDelayToRemoveObstaclePanel = 0;
                }
            }
            else if (stage == 1){
                clickCountLabel.setText(clickCount + "m");
            }

            if(stage == 1 && !button2Unlocked && clickCount >= PRICE_TO_UNLOCK_TOW_TRUCK) {
                button2Unlocked = true;
                button2.setText("Tow Truck");
            }
            if(stage == 1 && clickCount >= PRICE_TO_UNLOCK_MECHANIC) {
                clickCount = PRICE_TO_UNLOCK_MECHANIC;
                timer.stop();
                perSecondLabelLabel.setText("");
                perSecondLabel.setText("");
                price4.setText(CLICKS_TO_FIX_CAR + "m");
                temporarilyLockButtonsForStageAdvance(1);
            }
        });
    }

    private void adjustSpeedForPitch() {
        if (hasTookOff) {
            switch (currentPitch) {
                case 0 -> newMaxSpeedAdjustedForPitch = 130;
                case 1 -> newMaxSpeedAdjustedForPitch = 95;
                case 2 -> newMaxSpeedAdjustedForPitch = 65;
                case 3 -> newMaxSpeedAdjustedForPitch = 33;
                case 4 -> newMaxSpeedAdjustedForPitch = 17;
                case 5 -> newMaxSpeedAdjustedForPitch = 0;
                case 6 -> newMaxSpeedAdjustedForPitch = ((ORIGINAL_THRUST_ARRAY[thrustLevel][1] / 9) * 7) - ORIGINAL_THRUST_ARRAY[thrustLevel][1];
                case 7 -> newMaxSpeedAdjustedForPitch = ((ORIGINAL_THRUST_ARRAY[thrustLevel][1] / 9) * 6) - ORIGINAL_THRUST_ARRAY[thrustLevel][1];
                case 8 -> newMaxSpeedAdjustedForPitch = ((ORIGINAL_THRUST_ARRAY[thrustLevel][1] / 9) * 5) - ORIGINAL_THRUST_ARRAY[thrustLevel][1];
                case 9 -> newMaxSpeedAdjustedForPitch = (ORIGINAL_THRUST_ARRAY[thrustLevel][1] / 2) - ORIGINAL_THRUST_ARRAY[thrustLevel][1];
                case 10 -> newMaxSpeedAdjustedForPitch = (ORIGINAL_THRUST_ARRAY[thrustLevel][1] / 3) - ORIGINAL_THRUST_ARRAY[thrustLevel][1];
            }
            THRUST_ARRAY[thrustLevel][1] = ORIGINAL_THRUST_ARRAY[thrustLevel][1] + newMaxSpeedAdjustedForPitch;
            THRUST_ARRAY_ADJUSTED_FOR_PITCH[thrustLevel][1] = ORIGINAL_THRUST_ARRAY[thrustLevel][1] + newMaxSpeedAdjustedForPitch;
            if (altitudeZone == 5) {
                System.out.println("Speed adjusts to: " + THRUST_ARRAY[thrustLevel][1] + " and current stall speed = " + ALTITUDE_STALL_MAX_SPEED_RELATIONSHIP_ARRAY[altitudeZone][1] + " you wont get higher than FL450 " + "Recovery Speed should be: " + (ALTITUDE_STALL_MAX_SPEED_RELATIONSHIP_ARRAY[altitudeZone][1] + STALL_RECOVERY_COEFFICIENT));
            } else {
                System.out.println("Speed adjusts to: " + THRUST_ARRAY[thrustLevel][1] + " and current stall speed = " + ALTITUDE_STALL_MAX_SPEED_RELATIONSHIP_ARRAY[altitudeZone][1] + " and next stall speed = " + ALTITUDE_STALL_MAX_SPEED_RELATIONSHIP_ARRAY[altitudeZone+1][1] + "Recovery Speed should be: " + (ALTITUDE_STALL_MAX_SPEED_RELATIONSHIP_ARRAY[altitudeZone][1] + STALL_RECOVERY_COEFFICIENT));
            }
        }
    }

    private void calculateAltitude() {
        switch (currentPitch) {
            case 0, 10 -> altitudeGain = ((indicatedAirspeedValue / ALTITUDE_COEFFICIENT) * 1.33) / 1000;
            case 1, 9 -> altitudeGain = ((indicatedAirspeedValue / ALTITUDE_COEFFICIENT)) / 1000;
            case 2, 8 -> altitudeGain = ((indicatedAirspeedValue / ALTITUDE_COEFFICIENT) * 0.67) / 1000;
            case 3, 7 -> altitudeGain = ((indicatedAirspeedValue / ALTITUDE_COEFFICIENT) * 0.45) / 1000;
            case 4, 6 -> altitudeGain = ((indicatedAirspeedValue / ALTITUDE_COEFFICIENT) * 0.22) / 1000;
            case 5 -> altitudeGain = 0;
        }
        switch (currentPitch) {
            case 0,1,2,3,4 -> altitude = (altitude - altitudeGain);
            case 5,6,7,8,9,10 -> altitude = (altitude + altitudeGain);
        }
        if (altitude > ALTITUDE_STALL_MAX_SPEED_RELATIONSHIP_ARRAY[altitudeZone][0] && altitude <= ALTITUDE_STALL_MAX_SPEED_RELATIONSHIP_ARRAY[5][0]) {
            altitudeZone++;
            altitudeZoneChangedFlag = true; //sraheel this flag affects the calling of the method adjustSpeedForAltitudeZone()
        }
    }

    private void adjustSpeedForAltitudeZone() { //sraheel this method affects it
        if (altitudeZone > 0) {
            double altitudeDifferenceBetweenAltitudeZones = ALTITUDE_STALL_MAX_SPEED_RELATIONSHIP_ARRAY[altitudeZone][0] - ALTITUDE_STALL_MAX_SPEED_RELATIONSHIP_ARRAY[altitudeZone - 1][0];
            double altitudeDifferenceBetweenCurrentAndPreviousAltitudeMax = altitude - ALTITUDE_STALL_MAX_SPEED_RELATIONSHIP_ARRAY[altitudeZone - 1][0];
            altitudeProportion = (100 / altitudeDifferenceBetweenAltitudeZones) * altitudeDifferenceBetweenCurrentAndPreviousAltitudeMax;
            if (THRUST_ARRAY[thrustLevel][1] > ((THRUST_ARRAY_ADJUSTED_FOR_PITCH[thrustLevel][1] / 100)) * (100 - altitudeProportion)) { //sraheel I think the core problem is in this if condition
                THRUST_ARRAY[thrustLevel][1] = THRUST_ARRAY[thrustLevel][1] * (100 - altitudeProportion); // sraheel this sets the speed on each "tick" until it reduces down to altitudeProportion%
            } else {
                altitudeZoneChangedFlag = false;
            }
            altitudeAdjustedThrustArrayValue = THRUST_ARRAY[thrustLevel][1];
        }
    }

    private void recoverStallCheck() {
        stallingWarning.setText("STALLING! TO RECOVER, ACCELERATE TO AT LEAST " + (ALTITUDE_STALL_MAX_SPEED_RELATIONSHIP_ARRAY[altitudeZone][1] + STALL_RECOVERY_COEFFICIENT) + "IAS.");
        if (indicatedAirspeedValue >= (ALTITUDE_STALL_MAX_SPEED_RELATIONSHIP_ARRAY[altitudeZone][1] + STALL_RECOVERY_COEFFICIENT)) {
            buttonAuxiliary.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f), Color.red));
            buttonAuxiliary.setText("Recover");
            buttonAuxiliary.setActionCommand("RecoverStall");
            buttonAuxiliary.setVisible(true);
        }
        if (altitude <= 0) {
            gameOver();
        }
    }

    private void stallSetup() {
        temporarilyLockButtonsForStageAdvance(2);
        stallingWarningPanel.setVisible(true);
        recoverySpeed = ALTITUDE_STALL_MAX_SPEED_RELATIONSHIP_ARRAY[altitudeZone][1] + STALL_RECOVERY_COEFFICIENT;
        pitchInfo.setVisible(false);
        pitchHUDDisplay.setImage(pitch3);
        pitchInfo.setVisible(true);
        button1.setText("Thrust +");
        button1.setActionCommand("ThrustUp");
        for (int i = 0; i < THRUST_ARRAY.length; i++) { //sets thrust level to be nearest to keep speed similar to require user action
            if (indicatedAirspeedValue <= ORIGINAL_THRUST_ARRAY[i][1] && indicatedAirspeedValue > ORIGINAL_THRUST_ARRAY[i - 1][1]) {
                if (thrustLevel < 10) {
                    thrustLevel = (int) ORIGINAL_THRUST_ARRAY[10][0];
                } else {
                    thrustLevel = (int) ORIGINAL_THRUST_ARRAY[i][0];
                }
                break;
            }
        }
        currentPitch = 3;
        stallActive = 2; //stall set up and active
    }

    private void stallCheck() {
        if (indicatedAirspeedValue < ALTITUDE_STALL_MAX_SPEED_RELATIONSHIP_ARRAY[altitudeZone][1]) {
            stallActive = 1; //trigger stall setup phase
            System.out.println("Stalling Activated!");
        }
    }

    private void setupNextObstacle(int passObstacleFlag, int distanceToEndOfStageGoalAfterFailure) {
        if (stage != 4) {
            if (passObstacleFlag == 2) {
                if (failedObstacleWithinApproachOfEndOfStageGoal) {
                    endOfStageGoalApproach(distanceToEndOfStageGoalAfterFailure, approachTrigger);
                }
                clicksPerSecond = 0;
            }
        }
        wasPassingNowFailing = false;
        perSecondLabel.setText(clicksPerSecond + "m/s");
        timerUpdate(nitroBeingUsed, thrustLevel);
        if (stage == 3) {
            int value = (int) (Math.random() * NUMBER_OF_OBSTACLES_IN_AIRPORT_DASH_ARRAY_MINUS_1) + 1; //pick a new obstacle
            obstacleType = obstacleNameArrayStg3[value];
            obstacleTarget = value;
            countDownToPassObstacleOn = false;
            whatIsNextObstacle.setText("");
            nextObstDistance = (int) ((Math.random() * (MAX_NEW_OBSTACLE_DISTANCE_STG3 - MIN_NEW_OBSTACLE_DISTANCE_STG3)) + MIN_NEW_OBSTACLE_DISTANCE_STG3); //distance of next obstacle
            nextSuccessfulObstacleLeavesDistance = distanceToEndOfStageGoal - nextObstDistance;
            int count = 0;
            if (approachTrigger == 0 && passObstacleFlag != 2) {
                if ((nextSuccessfulObstacleLeavesDistance - MIN_NEW_OBSTACLE_DISTANCE_STG3) < 3000) {
                    nextObstDistance = distanceToEndOfStageGoal - 5000;
                    approachTrigger = 1;
                    approachFlag = true;
                }
            } else if (approachTrigger == 1) {
                if (passObstacleFlag != 2) {
                    while (nextSuccessfulObstacleLeavesDistance <= 1500) {
                        nextObstDistance = (int) ((Math.random() * (MAX_NEW_OBSTACLE_DISTANCE_STG3 - MIN_NEW_OBSTACLE_DISTANCE_WHILE_APPROACHING_STG3)) + MIN_NEW_OBSTACLE_DISTANCE_WHILE_APPROACHING_STG3); //distance of next obstacle
                        nextSuccessfulObstacleLeavesDistance = distanceToEndOfStageGoal - nextObstDistance;
                        approachTrigger = 2;
                        count++;
                    }
                }
            } else if (approachTrigger == 2) {
                if (passObstacleFlag != 2) {
                    approachTrigger = 3;
                }
            }
            System.out.println("approachTrigger" + approachTrigger + " loop ran " + count + " times!");
            if (!approachingEndOfStageGoalFlag) {
                distanceToNextObstacleLabel.setText((int) nextObstDistance + "m");
            }
            if ((value == 5 || value == 6 || value == 7 || value == 8) && ((nitroSpeedUp1 || nitro1) || (nitroSpeedUp2 || nitro2) || (nitroSpeedUp3 || nitro3) || (nitroSpeedUp4 || nitro4) || (nitroSpeedUp5 || nitro5))) { //if an obstacle that can or must be attained with nitros
                originalTimerObstacleValue = 1;
            } else {
                originalTimerObstacleValue = (int) ((Math.random() * (MAX_NEW_TIMER_AMOUNT - MIN_NEW_TIMER_AMOUNT)) + MIN_NEW_TIMER_AMOUNT);
            }
            timerObstacleValue = originalTimerObstacleValue;
            if (!approachFlag) {
                costOfFailureValue = (int) (Math.random() * (clickCount / 100 * COST_OF_FAILURE_FURTHER_ITERATIONS_FACTOR1)) + (clickCount / 100 * COST_OF_FAILURE_FURTHER_ITERATIONS_FACTOR2);
            } else {
                costOfFailureValue = COST_OF_FAILURE_FOR_FAILING_APPROACH_OR_END_OF_STAGE_OBSTACLE;
            }
        } else if (stage == 4) {
            int value = (int) (Math.random() * NUMBER_OF_OBSTACLES_IN_FLIGHT_ARRAY_MINUS_1) + 1; //pick a new obstacle
            obstacleType = obstacleNameArrayStg4[value];
            obstacleTarget = value;
            countDownToPassObstacleOn = false;
            whatIsNextObstacle.setText("");
            nextObstDistance = (int) ((Math.random() * (MAX_NEW_OBSTACLE_DISTANCE_STG4 - MIN_NEW_OBSTACLE_DISTANCE_STG4)) + MIN_NEW_OBSTACLE_DISTANCE_STG4); //distance of next obstacle
            nextSuccessfulObstacleLeavesDistance = distanceToEndOfStageGoal - nextObstDistance;
            if (approachTrigger == 0 && passObstacleFlag != 2) {
                if ((nextSuccessfulObstacleLeavesDistance - MIN_NEW_OBSTACLE_DISTANCE_STG4) < 15000) {
                    nextObstDistance = distanceToEndOfStageGoal - 40000;
                    approachTrigger = 1;
                    approachFlag = true;
                }
            } else if (approachTrigger == 1) {
                if (passObstacleFlag != 2) {
                    approachTrigger = 2;
                }
            }
            if (!approachingEndOfStageGoalFlag) {
                distanceToNextObstacleLabel.setText((int) nextObstDistance + "m");
            }
            originalTimerObstacleValue = (int) ((Math.random() * (MAX_NEW_TIMER_AMOUNT - MIN_NEW_TIMER_AMOUNT)) + MIN_NEW_TIMER_AMOUNT);
            timerObstacleValue = originalTimerObstacleValue;
            if (!approachFlag) {
                costOfFailureValue = (int) (Math.random() * (clickCount / 100 * COST_OF_FAILURE_FURTHER_ITERATIONS_FACTOR1)) + (clickCount / 100 * COST_OF_FAILURE_FURTHER_ITERATIONS_FACTOR2);
            } else {
                costOfFailureValue = COST_OF_FAILURE_FOR_FAILING_APPROACH_OR_END_OF_STAGE_OBSTACLE;
            }
        } else {
                endOfStageGoalApproach(distanceToEndOfStageGoalAfterFailure, approachTrigger);
        }

        if (stage == 3 && (approachTrigger == 1 || approachTrigger == 2 || approachTrigger == 3)) {
            endOfStageGoalApproach(distanceToEndOfStageGoalAfterFailure, approachTrigger);
        }
        if (stage == 4 && (approachTrigger == 1)) {
            endOfStageGoalApproach(distanceToEndOfStageGoalAfterFailure, approachTrigger);
        }
        if (stage == 2 && distanceToEndOfStageGoal > 2500) {
            requiredLeftClicks = (int) ((Math.random() * (MAX_NO_OF_TURNING_CLICKS - MIN_NO_OF_TURNING_CLICKS)) + MIN_NO_OF_TURNING_CLICKS);
            if (requiredLeftClicks == 0) { //remove possibility of L:0 | R:0 random generation
                requiredRightClicks = (int) ((Math.random() * (MAX_NO_OF_TURNING_CLICKS - MIN_NO_OF_TURNING_CLICKS - 1)) + MIN_NO_OF_TURNING_CLICKS + 1);
            } else {
                requiredRightClicks = (int) ((Math.random() * (MAX_NO_OF_TURNING_CLICKS - MIN_NO_OF_TURNING_CLICKS)) + MIN_NO_OF_TURNING_CLICKS);
            }
            int value = (int) (Math.random() * NUMBER_OF_OBSTACLES_IN_STEVE_ARRAY_MINUS_1) + 1; //pick a new obstacle
            obstacleType = obstacleNameArrayStg2[value];
            obstacleTarget = value;
            countDownToPassObstacleOn = false;
            whatIsNextObstacle.setText("");
            nextObstDistance = (int) ((Math.random() * (MAX_NEW_OBSTACLE_DISTANCE_STG2 - MIN_NEW_OBSTACLE_DISTANCE_STG2)) + MIN_NEW_OBSTACLE_DISTANCE_STG2); //distance of next obstacle
            if (!approachingEndOfStageGoalFlag) {
                distanceToNextObstacleLabel.setText((int) nextObstDistance + "m");
            }
            originalTimerObstacleValue = (int) ((Math.random() * (MAX_NEW_TIMER_AMOUNT - MIN_NEW_TIMER_AMOUNT)) + MIN_NEW_TIMER_AMOUNT);
            timerObstacleValue = originalTimerObstacleValue;
            if (costOfFailureFirstIterationFlag) {
                costOfFailureValue = (int) (Math.random() * (clickCount/100 * COST_OF_FAILURE_FIRST_ITERATION_FACTOR)) + 200;
            } else {
                costOfFailureValue = (int) (Math.random() * (clickCount/100 * COST_OF_FAILURE_FURTHER_ITERATIONS_FACTOR1)) + (clickCount/100 * COST_OF_FAILURE_FURTHER_ITERATIONS_FACTOR2);
            }
        } else {
            if (!approachFlag) {
                endOfStageGoalApproach(distanceToEndOfStageGoalAfterFailure, approachTrigger);
            }
        }
    }

    private void endOfStageGoalApproach(int distanceToEndOfStageGoalAfterFailure, int approachTrigger) {
        if ((stage == 2 && failedObstacleWithinApproachOfEndOfStageGoal && distanceToEndOfStageGoalAfterFailure < 2500)  || (stage == 2 && distanceToEndOfStageGoal < 2500)) {
            approachingEndOfStageGoalFlag = true;
            generalTimerElapsedLabel.setText("Steve will wait for you!");
            costOfFailureValue = COST_OF_FAILURE_FOR_FAILING_APPROACH_OR_END_OF_STAGE_OBSTACLE;
            obstacleType = obstacleNameArrayStg2[20];
            obstacleTarget = 20;
            distanceToGoTitleLabel.setText("STOP WITHIN 10m OF STEVE!");
            generalTimerElapsedValue.setForeground(Color.green);
            generalTimerElapsedValue.setText("WAITING");
            whatIsNextObstacle.setText("");
            nextObstDistance = distanceToEndOfStageGoal; //distance of next obstacle
            distanceToNextObstacleTitleLabel.setText("");
            distanceToNextObstacleLabel.setText("");
            distanceToGoTitleLabel.setForeground(Color.red);
            countDownToPassObstacleOn = false;
            originalTimerObstacleValue = 3;
            timerObstacleValue = originalTimerObstacleValue;
        }
        if ((stage == 3 && failedObstacleWithinApproachOfEndOfStageGoal && distanceToEndOfStageGoalAfterFailure < 3000) || (stage == 3 && approachFlag)) {
            if (approachTrigger == 1) {
                obstacleType = obstacleNameArrayStg3[9];
                obstacleTarget = 9;
                whatIsNextObstacle.setText(obstacleType);
                distanceToNextObstacleTitleLabel.setText("Distance to obstacle:");
                distanceToNextObstacleLabel.setText((int) nextObstDistance + "m");
                originalTimerObstacleValue = 3;
                timerObstacleValue = originalTimerObstacleValue;
            }
            if (approachTrigger == 2) {
                obstacleType = obstacleNameArrayStg3[10];
                obstacleTarget = 10;
                whatIsNextObstacle.setText(obstacleType);
                distanceToNextObstacleTitleLabel.setText("Distance to obstacle:");
                distanceToNextObstacleLabel.setText((int) nextObstDistance + "m");
                originalTimerObstacleValue = 3;
                timerObstacleValue = originalTimerObstacleValue;
            }
            if (approachTrigger == 3) {
                approachFlag = false;
                approachingEndOfStageGoalFlag = true;
                generalTimerElapsedLabel.setText("The escape plane is ready!");
                generalTimerElapsedValue.setForeground(Color.green);
                generalTimerElapsedValue.setText("WAITING");
                obstacleType = obstacleNameArrayStg3[11];
                obstacleTarget = 11;
                distanceToGoTitleLabel.setText("STOP WITHIN 10m OF THE PLANE!");
                whatIsNextObstacle.setText("");
                nextObstDistance = distanceToEndOfStageGoal; //distance of next obstacle
                distanceToNextObstacleTitleLabel.setText("");
                distanceToNextObstacleLabel.setText("");
                distanceToGoTitleLabel.setForeground(Color.red);
                countDownToPassObstacleOn = false;
                originalTimerObstacleValue = 3;
                timerObstacleValue = originalTimerObstacleValue;
            }
        }
        if ((stage == 4 && failedObstacleWithinApproachOfEndOfStageGoal && distanceToEndOfStageGoalAfterFailure < 20000) || (stage == 4 && approachFlag)) {
            if (approachTrigger == 1) {
                obstacleType = obstacleNameArrayStg4[6];
                obstacleTarget = 6;
                costOfFailureValue = COST_OF_FAILURE_FOR_FAILING_DROPZONE_STAGE4;
                distanceToGoTitleLabel.setText("DROP GOODS WITHIN 1000m OF DROPZONE!");
                generalTimerElapsedValue.setForeground(Color.green);
                generalTimerElapsedValue.setText("PREPARING!");
                whatIsNextObstacle.setText(obstacleType);
                distanceToNextObstacleTitleLabel.setText("Distance to obstacle:");
                distanceToNextObstacleLabel.setText((int) nextObstDistance + "m");
                originalTimerObstacleValue = 5;
                timerObstacleValue = originalTimerObstacleValue;
            }
            if (approachTrigger == 2) {
                approachFlag = false;
                approachingEndOfStageGoalFlag = true;
                generalTimerElapsedLabel.setText("Prepare to Jump!");
                costOfFailureValue = COST_OF_FAILURE_FOR_FAILING_JUMPZONE_STAGE4;
                obstacleType = obstacleNameArrayStg4[7];
                obstacleTarget = 7;
                distanceToGoTitleLabel.setText("JUMP WITHIN 1000m OF LAND ZONE!");
                generalTimerElapsedValue.setForeground(Color.green);
                generalTimerElapsedValue.setText("PREPARING!");
                whatIsNextObstacle.setText("");
                nextObstDistance = distanceToEndOfStageGoal;
                distanceToNextObstacleTitleLabel.setText("");
                distanceToNextObstacleLabel.setText("");
                distanceToGoTitleLabel.setForeground(Color.red);
                countDownToPassObstacleOn = false;
                originalTimerObstacleValue = 1;
                timerObstacleValue = originalTimerObstacleValue;
            }
        }
    }

    private void displayObstaclePassConditions(int count) {
        if (count == 1) { //bug fix for when passing and no button clicks allowing passing of next obstacle
            double originalSpeed = clicksPerSecond;
            adjustmentFlag = true;
            if (stage == 4 && hasTookOff) {
                button2.doClick();
                button1.doClick();
            } else if (stage == 2 || stage == 3) {
                button2.doClick();
                button1.doClick();
                if (originalSpeed > clicksPerSecond && !degradeNitro1 && !degradeNitro2 && !degradeNitro3 && !degradeNitro4 && !degradeNitro5) {
                    while (originalSpeed > clicksPerSecond) {
                        button1.doClick();
                    }
                }
                if (originalSpeed < clicksPerSecond) {
                    while (originalSpeed < clicksPerSecond) {
                        button2.doClick();
                    }
                }
            }
            adjustmentFlag = false;
            Integer mS = ((int) clicksPerSecond);
            speedKmH = clicksPerSecond * 3.6;
            speedKnots = speedKmH * 0.539957;
            String kmHr = String.format("%.1f", speedKmH);
            String knots = String.format("%.1f", speedKnots);
            if (stage ==2 || stage == 3) {
                perSecondLabelLabel.setText("Speed:");
                perSecondLabel.setText(mS + "m/s (" + kmHr + "km/hr)");
            } else if (stage == 4) {
                perSecondLabelLabel.setText("Ground Speed:");
                perSecondLabel.setText(mS + "m/s (" + knots + "knots)");
            }
        } //end of bug fix

        if (costOfFailureValue == 0) { //if leaving garage and Garage Gate
                costOfFailureFirstIterationFlag = true;
                costOfFailureValue = FIRST_OBSTACLE_STAGE2; //lose all clicks if fail first obstacle
        }
        obstacleConditionsTitle.setText("Speed Range:");
        if (stage == 2) {
            obstacleConditions.setText(SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][0] + " - " + SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][1] + "m/s");
            if (leftClickCount == 0 && rightClickCount == 0 && SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][2] == 1) {
                passFailObstacle.setForeground(Color.yellow);
                passFailObstacle.setFont(font1);
                passFailObstacle.setText("L: " + requiredLeftClicks + " | R: " + requiredRightClicks);
            }
            if (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][2] == 0) {
                passFailObstacle.setForeground(Color.yellow);
                passFailObstacle.setFont(font2);
                passFailObstacle.setText("NO TURNING REQUIRED");
            }
        } else if (stage == 3) {
            obstacleConditions.setText(SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][0] + " - " + SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][1] + "m/s");
        } else if (stage == 4 && !gameOverFlag) {
            obstacleConditions.setText(SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG4[obstacleTarget][0] + " - " + SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG4[obstacleTarget][1] + "m/s");
          }
        if (stage == 2 || stage == 3 && !gameOverFlag) {
            timerObstacleTitle.setText("Time to hold Speed:");
            timerObstacle.setText(timerObstacleValue + "s");
        } else if (stage == 4 && !gameOverFlag) {
            timerObstacleTitle.setText("Altitude to hold:");
            timerObstacle.setText("Alt: " + (ALTITUDE_RANGE_REQUIRED_OBSTACLES_ARRAY_STG4[obstacleTarget][0] * 100) + " - " + (ALTITUDE_RANGE_REQUIRED_OBSTACLES_ARRAY_STG4[obstacleTarget][1] * 100) + "ft");
        }
        if (stage == 4 && obstacleTarget == 0) {
            costOfFailure.setText("Cost of Failure: GAME OVER!");
        } else {
            costOfFailure.setText("Cost of Failure: " + costOfFailureValue + "m");
        }
        whatIsNextObstacle.setText("Next obstacle: " + obstacleType);
    }

    public void obstaclePassed() {
        leftClickCount = 0;
        rightClickCount = 0;
        passFailObstacle.setForeground(Color.green);
        passFailObstacle.setFont(font3);
        if (stage == 4 && obstacleTarget == 0) {
            hasTookOff = true;
        }
        if (!approachingEndOfStageGoalFlag && !landingGearUpFlag) {
            passFailObstacle.setText("TAKEN OFF!!");
        } else if (!approachingEndOfStageGoalFlag) {
            passFailObstacle.setText("PASS");
        }else {
            generalElapsedCounter.stop();
            if (stage == 2) {
                passFailObstacle.setText("YOU MET STEVE!!");
            } else if (stage == 3) {
                passFailObstacle.setText("YOU ESCAPED!!");
                buttonAuxiliary.setVisible(false);
            } else if (stage == 4) {
                passFailObstacle.setText("YOU LANDED SAFELY!!");
                buttonAuxiliary.setVisible(false);
            }
        }
        startDelayTimerFlag = true;
        delayPanelAfterObstacleTimerUpdate();
        setupNextObstacle(passObstacleFlag, distanceToEndOfStageGoalAfterFail);
        passObstacleFlag = 0;
        startCountDownToPassObstacleFlag = false;
        countDownToPassObstacleTimer.stop();
        if ((stage == 2 || stage == 3) && approachingEndOfStageGoalFlag && distanceToEndOfStageGoal < 10) {
            temporarilyLockButtonsForStageAdvance(2);
        } else if (stage == 4 && approachingEndOfStageGoalFlag && distanceToEndOfStageGoal <= ACCEPTABLE_RANGE_TO_PARACHUTE) {
            temporarilyLockButtonsForStageAdvance(2);
        }
    }

    public void obstacleFailed() {
        if (approachFlag) {
            approachFlag = false;
            approachTrigger = 0;
        }
        if (stage == 3) {
            resetNitro();
        }
        originalCPS = 0;
        leftClickCount = 0;
        rightClickCount = 0;
        passFailObstacle.setForeground(Color.red);
        passFailObstacle.setFont(font3);
        if (((stage == 2 || stage == 3) && !approachingEndOfStageGoalFlag) || (stage == 4 && landingGearUpFlag)) {
            passFailObstacle.setText("FAIL");
            distanceToEndOfStageGoalAfterFail = distanceToEndOfStageGoal;
            if (distanceToEndOfStageGoal < 2500 && stage == 2 || distanceToEndOfStageGoal < 3000 && stage == 3) {
                failedObstacleWithinApproachOfEndOfStageGoal = true;
            }
        } else {
            if (stage == 2) {
                gameOver();
            } else if (stage == 3) {
                gameOver();
            } else if (stage == 4) {
                gameOver();
            }
            if ((stage == 2 || stage == 3) && !gameOverFlag) {
                failedObstacleWithinApproachOfEndOfStageGoal = false;
                unsetEndOfStageGoalApproach();
            }
        }
        if (!gameOverFlag) {
            startDelayTimerFlag = true;
            delayPanelAfterObstacleTimerUpdate();
            if (clickCount >= costOfFailureValue) { //subtract clicks for failing
                clickCount = clickCount - costOfFailureValue;
            } else { // set clicks to zero if garage gate
                clickCount = 0;
            }
            distanceToEndOfStageGoal = distanceToEndOfStageGoal + costOfFailureValue;
            if (costOfFailureFirstIterationFlag) {
                costOfFailureFirstIterationFlag = false;
            } else {
                countDownToPassObstacleTimer.stop();
            }
            countDownToPassObstacleOn = false;
            setupNextObstacle(passObstacleFlag, distanceToEndOfStageGoalAfterFail);
            passObstacleFlag = 0;
            startCountDownToPassObstacleFlag = false;
            if (approachingEndOfStageGoalFlag) { //fail actually on goal
                if (!failedObstacleWithinApproachOfEndOfStageGoal) {
                    unsetEndOfStageGoalApproach();
                }
            } else if (failedObstacleWithinApproachOfEndOfStageGoal) { //fail previous to goal but within range
                endOfStageGoalApproach(distanceToEndOfStageGoalAfterFail, approachTrigger);
            }
        }
    }

    private void unsetEndOfStageGoalApproach() {
        approachingEndOfStageGoalFlag = false;
        generalTimerElapsedLabel.setForeground(Color.yellow);
        if (stage == 2) {
            generalTimerElapsedLabel.setText("Steve Moves Every " + (TIME_UNTIL_PENALTY_STAGE2 / 60) + " Minutes:");
            distanceToGoTitleLabel.setText("Distance to Steve:");
        } else if (stage == 3) {
            generalTimerElapsedLabel.setText("Escape Aircraft Leaves In 10 min!");
            distanceToGoTitleLabel.setText("Distance to Escape Plane:");
        }
        distanceToGoTitleLabel.setForeground(Color.yellow);
        distanceToNextObstacleTitleLabel.setText("Distance to obstacle:");
        generalTimerElapsedValue.setForeground(Color.white);
        generalElapsedCounter.start();
        if (!moreThanOneMinuteElapsedFlag && generalTimerElapsedSeconds < 60 && !gameOverFlag) {
            if (generalTimerElapsedSeconds < 10) {
                generalTimerElapsedValue.setText("0:0" + generalTimerSecondsToDisplay);
            } else {
                generalTimerElapsedValue.setText("0:" + generalTimerSecondsToDisplay);
            }
        } else {
            if (generalTimerSecondsToDisplay < 10 && !gameOverFlag) {
                generalTimerElapsedValue.setText(generalTimerElapsedMinutes + ":0" + generalTimerSecondsToDisplay);
            } else {
                generalTimerElapsedValue.setText(generalTimerElapsedMinutes + ":" + generalTimerSecondsToDisplay);
            }
        }
    }

    public void delayPanelAfterObstacleTimerUpdate() {
        if(!delayObstaclePanelOn) {
            delayObstaclePanelOn=true;
        }
        else {
            delayPanelAfterObstacleTimer.stop();
        }

        if(startDelayTimerFlag) {
            setDelayPanelAfterObstacleTimer();
            delayPanelAfterObstacleTimer.start();
            startDelayTimerFlag = false;
        }
    }

    public void countDownToPassObstacleTimerUpdate() {
        if(!countDownToPassObstacleOn) {
            countDownToPassObstacleOn=true;
        }
        else {
            countDownToPassObstacleTimer.stop();
        }

        if(startCountDownToPassObstacleFlag) {
            countDownToPassObstacleTimer.start();
            System.out.println("countdownTimerStarted, time is" + timerObstacleValue + "s");
            startCountDownToPassObstacleFlag = false;
        }
        if (stage == 2) {
            if(passObstacleFlag == 1 && SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][2] == 1 && requiredLeftClicks == leftClickCount && requiredRightClicks == rightClickCount) {
                countDownToPassObstacleTimer.stop();
                obstaclePassed();
            }
            if(passObstacleFlag == 1 && SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][2] == 0) {
                countDownToPassObstacleTimer.stop();
                obstaclePassed();
            }
            if(passObstacleFlag == 1 && SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][2] == 1 && (requiredLeftClicks != leftClickCount || requiredRightClicks != rightClickCount)) {
                passObstacleFlag = 2;
                countDownToPassObstacleTimer.stop();
                obstacleFailed();
            }
        } else if (stage == 3) {
            if (passObstacleFlag == 1) {
                countDownToPassObstacleTimer.stop();
                obstaclePassed();
            }
        }
        if (passObstacleFlag == 2) {
            obstacleFailed();
        }
        if ((speedRangeActual > speedRangePermitted || speedRangeActual < 0) && nextObstDistance > 0) {
            countDownToPassObstacleTimer.stop();
            timerObstacleValue = originalTimerObstacleValue;
            if (!Objects.equals(timerObstacle.getText(), "") && (stage == 2 || stage == 3)) { //if already showing obstacle panel info, then run this, otherwise no, to fix flickering seconds after passing
                timerObstacle.setText(timerObstacleValue + "s");
            }
            countDownToPassObstacleOn = false;
        }
    }
    public void timerUpdate(int nitroBeingUsed, int thrustLevel) {
        if (!timerOn && clickCount < PRICE_TO_UNLOCK_MECHANIC && !carInMechanic) { //WATCH FOR ISSUES HERE ABOVE 3500M STAGE 2
            timerOn = true;
        } else if (timerOn) {
            timer.stop();
        }

        if (stage == 3 && nitroBeingUsed == 1 && nitroSpeedUp1 && !degradeNitro1) {
            timerSpeed = 5;
            if (nitroTicks1 >= BOOST_AMOUNT_DELIVERED) {
                nitroSpeedUp1 = false;
                nitro1 = true;
                originalGeneralTimerValue1 = generalTimerElapsedSeconds;
                preNitroCPS1 = clicksPerSecond;
                nitroTicks1 = 0;
            }
        }
        if (stage == 3 && nitroBeingUsed == 2 && nitroSpeedUp2 && !degradeNitro2) {
            timerSpeed = 5;
            if (nitroTicks2 >= BOOST_AMOUNT_DELIVERED) {
                nitroSpeedUp2 = false;
                nitro2 = true;
                originalGeneralTimerValue2 = generalTimerElapsedSeconds;
                preNitroCPS2 = clicksPerSecond;
                nitroTicks2 = 0;
            }
        }
        if (stage == 3 && nitroBeingUsed == 3 && nitroSpeedUp3 && !degradeNitro3) {
            timerSpeed = 5;
            if (nitroTicks3 >= BOOST_AMOUNT_DELIVERED) {
                nitroSpeedUp3 = false;
                nitro3 = true;
                originalGeneralTimerValue3 = generalTimerElapsedSeconds;
                preNitroCPS3 = clicksPerSecond;
                nitroTicks3 = 0;
            }
        }
        if (stage == 3 && nitroBeingUsed == 4 && nitroSpeedUp4 && !degradeNitro4) {
            timerSpeed = 5;
            if (nitroTicks4 >= BOOST_AMOUNT_DELIVERED) {
                nitroSpeedUp4 = false;
                nitro4 = true;
                originalGeneralTimerValue4 = generalTimerElapsedSeconds;
                preNitroCPS4 = clicksPerSecond;
                nitroTicks4 = 0;
            }
        }
        if (stage == 3 && nitroBeingUsed == 5 && nitroSpeedUp5 && !degradeNitro5) {
            timerSpeed = 5;
            if (nitroTicks5 >= BOOST_AMOUNT_DELIVERED) {
                nitroSpeedUp5 = false;
                nitro5 = true;
                originalGeneralTimerValue5 = generalTimerElapsedSeconds;
                preNitroCPS5 = clicksPerSecond;
                nitroTicks5 = 0;
            }
        }

        if (nitroSpeedUp1) {
                clicksPerSecond++;
                nitroTicks1++;
        }
        if (nitroSpeedUp2) {
            clicksPerSecond++;
            nitroTicks2++;
        }
        if (nitroSpeedUp3) {
            clicksPerSecond++;
            nitroTicks3++;
        }
        if (nitroSpeedUp4) {
            clicksPerSecond++;
            nitroTicks4++;
        }
        if (nitroSpeedUp5) {
            clicksPerSecond++;
            nitroTicks5++;
        }
        if (stage == 4 && hasTookOff && !landingGearUpFlag) {
            buttonAuxiliary.setVisible(true);
        }
        if (stage == 4 && clickCount > ripGearOffValue - 500 && !landingGearUpFlag) {
            buttonAuxiliary.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f), Color.red));
        }
        if (stage == 4 && !landingGearUpFlag && clicksPerSecond > 100 || (stage == 4 && clickCount > ripGearOffValue && !landingGearUpFlag)) {
            rippedGearOff = true;
            gameOver();
        }
        if (stage == 4 && !speedDegrading && clicksPerSecond != THRUST_ARRAY[thrustLevel][1]) {
            currentSpeedProportionOfMaxForThrustLevel = (clicksPerSecond / THRUST_ARRAY[thrustLevel][1]) * 100;
            finalProportionPercentage = (100 - currentSpeedProportionOfMaxForThrustLevel) / 100;
            if (thrustLevel < 5) {
                accelerationAmount = (JET_ENGINE_COEFFICIENT * JET_ENGINE_COEFFICIENT_MODIFIER) * finalProportionPercentage;
            } else {
                accelerationAmount = JET_ENGINE_COEFFICIENT * finalProportionPercentage;
            }
            if (thrustLevel != 1) {
                accelerationAmount /= ACCELERATION_MODIFIER;
            }
            double speed = 1 / clicksPerSecond * 1000;
            timerSpeed = (int) Math.round(speed);
        }
        if (stage == 4 && speedDegrading && clicksPerSecond > THRUST_ARRAY[thrustLevel][1]) {
            if (currentSpeedProportionOfMaxForThrustLevel > 50 && clicksPerSecond >= THRUST_ARRAY[thrustLevel][1]) {
                accelerationAmount = -0.05;
            } else if ((currentSpeedProportionOfMaxForThrustLevel < 50 && clicksPerSecond >= THRUST_ARRAY[thrustLevel][1]) && (currentSpeedProportionOfMaxForThrustLevel >= 40 && clicksPerSecond >= THRUST_ARRAY[thrustLevel][1])) {
                accelerationAmount = -0.03;
            } else if ((currentSpeedProportionOfMaxForThrustLevel < 40 && clicksPerSecond >= THRUST_ARRAY[thrustLevel][1]) && (currentSpeedProportionOfMaxForThrustLevel >= 20 && clicksPerSecond >= THRUST_ARRAY[thrustLevel][1])) {
                accelerationAmount = -0.02;
            }  else if ((currentSpeedProportionOfMaxForThrustLevel < 20 && clicksPerSecond >= THRUST_ARRAY[thrustLevel][1]) && (currentSpeedProportionOfMaxForThrustLevel >= 1 && clicksPerSecond >= THRUST_ARRAY[thrustLevel][1])) {
                accelerationAmount = -0.01;
            }
            clicksPerSecond += accelerationAmount;
            double speed = 1 / clicksPerSecond * 1000;
            timerSpeed = (int) Math.round(speed);
        }

        if (stage == 2) {
            speedRangeActual = (int) clicksPerSecond - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][0];
            speedRangePermitted = (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][1] - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][0]);
        } else if (stage == 3) {
            speedRangeActual = (int) clicksPerSecond - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][0];
            speedRangePermitted = (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][1] - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][0]);
        } else if (stage == 4) {
            speedRangeActual = (int) clicksPerSecond - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG4[obstacleTarget][0];
            speedRangePermitted = (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG4[obstacleTarget][1] - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG4[obstacleTarget][0]);
        }
        if ((stage == 2 || stage == 3) && countDownToPassObstacleOn && nextObstDistance > 0 && (speedRangeActual > speedRangePermitted || speedRangeActual < 0)) {
            wasPassingNowFailing = true;
        }
        if ((stage == 4) && countDownToPassObstacleOn && nextObstDistance > 0 && (speedRangeActual > speedRangePermitted || speedRangeActual < 0) && (altitudeRangeActual > altitudeRangePermitted || altitudeRangeActual < 0)) {
            wasPassingNowFailing = true;
        }
        if (stage == 1) {
            String s = String.format("%.1f", clicksPerSecond);
            perSecondLabelLabel.setText("Metres of help per second:");
            perSecondLabel.setText(s);
            double speed = 1 / clicksPerSecond * 1000;
            timerSpeed = (int) Math.round(speed);
        } else if (stage == 2 || stage == 3 || stage == 4) {
            if (stage == 3 && degradeNitro1 && nitroBeingUsed != 0) {
                if (nitroTicks1 >= BOOST_AMOUNT_DELIVERED) {
                    nitroBeingUsed = 1;
                    degradeNitro1 = false;
                    nitro1 = false;
                    numberOfActiveNitros--;
                    nitrosArray[nitroBeingUsed-1] = false;
                    nitroTicks1 = 0;
                }
                if (degradeNitro1) {
                    timerSpeed = 10;
                    nitroTicks1++;
                    clicksPerSecond--;
                }
            }
            if (stage == 3 && degradeNitro2 && nitroBeingUsed != 0) {
                if (nitroTicks2 >= BOOST_AMOUNT_DELIVERED) {
                    nitroBeingUsed = 2;
                    degradeNitro2 = false;
                    nitro2 = false;
                    numberOfActiveNitros--;
                    nitrosArray[nitroBeingUsed-1] = false;
                    nitroTicks2 = 0;
                }
                if (degradeNitro2) {
                    timerSpeed = 10;
                    nitroTicks2++;
                    clicksPerSecond--;
                }
            }
            if (stage == 3 && degradeNitro3 && nitroBeingUsed != 0) {
                if (nitroTicks3 >= BOOST_AMOUNT_DELIVERED) {
                    nitroBeingUsed = 3;
                    degradeNitro3 = false;
                    nitro3 = false;
                    numberOfActiveNitros--;
                    nitrosArray[nitroBeingUsed-1] = false;
                    nitroTicks3 = 0;
                }
                if (degradeNitro3) {
                    timerSpeed = 10;
                    nitroTicks3++;
                    clicksPerSecond--;
                }
            }
            if (stage == 3 && degradeNitro4 && nitroBeingUsed != 0) {
                if (nitroTicks4 >= BOOST_AMOUNT_DELIVERED) {
                    nitroBeingUsed = 4;
                    degradeNitro4 = false;
                    nitro4 = false;
                    numberOfActiveNitros--;
                    nitrosArray[nitroBeingUsed-1] = false;
                    nitroTicks4 = 0;
                }
                if (degradeNitro4) {
                    timerSpeed = 10;
                    nitroTicks4++;
                    clicksPerSecond--;
                }
            }
            if (stage == 3 && degradeNitro5 && nitroBeingUsed != 0) {
                if (nitroTicks5 >= BOOST_AMOUNT_DELIVERED) {
                    nitroBeingUsed = 5;
                    degradeNitro5 = false;
                    nitro5 = false;
                    numberOfActiveNitros--;
                    nitrosArray[nitroBeingUsed-1] = false;
                    nitroTicks5 = 0;
                }
                if (degradeNitro5) {
                    timerSpeed = 10;
                    nitroTicks5++;
                    clicksPerSecond--;
                }
            }
            if (stage == 3 && nitroTicks1 == 0 && nitroTicks2 == 0 && nitroTicks3 == 0 && nitroTicks4 == 0 && nitroTicks5 == 0 && clicksPerSecond > 0 && !degradeNitro1) {
                double speed = 1 / clicksPerSecond * 1000;
                timerSpeed = (int) Math.round(speed);
            }
            Integer mS = ((int) clicksPerSecond);
            speedKmH = clicksPerSecond * 3.6;
            speedKnots = speedKmH * 0.539957;
            String kmHr = String.format("%.1f", speedKmH);
            String knots = String.format("%.1f", speedKnots);
            if (stage ==2 || stage == 3){
                perSecondLabelLabel.setText("Speed:");
                perSecondLabel.setText(mS + "m/s (" + kmHr + "km/hr)");
            } else {
                perSecondLabelLabel.setText("Ground Speed:");
                perSecondLabel.setText(mS + "m/s (" + knots + "knots)");
            }
        }

        if((!button3Unlocked && stage == 1 && clickCount < PRICE_TO_UNLOCK_MECHANIC) || ((stage == 2 || stage == 3 || stage == 4) && clicksPerSecond > 0)) {
            setTimer();
            timer.start();
        }
    }
    public class ClickHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            String action = event.getActionCommand();

            switch(action) {
                case "pushCar":
                if (!button3Unlocked && clickCount < PRICE_TO_UNLOCK_MECHANIC && stage == 1) {
                    clickCount++; //replace with clickCount++ for real, or clickCount = clickCount + 1750 for debug
                    clickCountLabel.setText(clickCount+"m");
                    if (!button1Unlocked && clickCount >= PRICE_TO_UNLOCK_HIRED_HELP) {
                        button1Unlocked = true;
                        button1.setText("Hired Help");
                        price1.setText(autoClickerPrice + "m");
                        price2.setText(towTruckPrice + "m");
                    }

                    if (!button2Unlocked && clickCount >= PRICE_TO_UNLOCK_TOW_TRUCK) {
                        button2Unlocked = true;
                        button2.setText("Tow Truck");
                        price3.setText(mechanicPrice + "m");
                    }

                }
                else if (stage == 1 && clickCount > PRICE_TO_UNLOCK_MECHANIC) {
                    clickCount=PRICE_TO_UNLOCK_MECHANIC;
                    clickCountLabel.setText(clickCount+"m");
                    timer.stop();
                    temporarilyLockButtonsForStageAdvance(1); //lock/unlock power ups while at mechanic
                }
                else {
                    if (stage == 1){
                    clickCount = PRICE_TO_UNLOCK_MECHANIC;
                    clickCountLabel.setText(clickCount+"m");
                    price4.setText(clicksLeftToFixCar + "m");
                    temporarilyLockButtonsForStageAdvance(1);
                    }
                }
                 //lock/unlock power ups while at mechanic
                if (BeginningOfADrivingStage) {
                        clickCount=0;
                        clickCountLabel.setText("0m");
                }
                break;
                case "Hired Help":
                    if(clickCount >= autoClickerPrice && !button3Unlocked && stage == 1){
                        clickCount = clickCount-autoClickerPrice;
                        autoClickerPrice = autoClickerPrice + COST_INCREASE_PER_HIRED_HELP;
                        clickCountLabel.setText(clickCount+"m");
                        price1.setText("" + autoClickerPrice + "m");
                        autoClickerNumber++;
                        button1.setText("Hired Help (" + autoClickerNumber + ")");
                        messageText.setText("Hired Help!\n[Price: " + autoClickerPrice + "]\nExtra manpower to push \nevery 10 seconds!");
                        clicksPerSecond = clicksPerSecond + HIRED_HELP_ADDS_THIS_MANY_CLICKS_PER_SECOND;
                        timerUpdate(nitroBeingUsed, thrustLevel);
                    }
                    else {
                        if (!mechanicTriggeredYet && stage == 1) {
                            messageText.setText("\n\nYou need " + (autoClickerPrice-clickCount) + " more metres\nto buy this upgrade!");
                        }
                        else {
                            messageText.setText("Already arrived at garage,\nno more hired help required");
                        }
                    }
                break;
                case "Tow Truck":
                    if(clickCount >= towTruckPrice && !button3Unlocked && stage == 1){
                        clickCount = clickCount-towTruckPrice;
                        towTruckPrice = towTruckPrice + COST_INCREASE_PER_TOW_TRUCK;
                        clickCountLabel.setText(clickCount+"m");
                        towTruckNumber++;
                        button2.setText("Tow Truck (" + towTruckNumber + ")");
                        price2.setText(towTruckPrice + "m");
                        messageText.setText("Call a Tow Truck!\n[Price: " + towTruckPrice + "]\nTow Truck will move you much\nfaster than a pair of hands!");
                        clicksPerSecond = clicksPerSecond + TOW_TRUCK_ADDS_THIS_MANY_CLICKS_PER_SECOND;
                        timerUpdate(nitroBeingUsed, thrustLevel);
                    }
                    else {
                        if (!mechanicTriggeredYet && stage == 1) {
                        messageText.setText("\n\nYou need " + (towTruckPrice-clickCount) + " more metres\nto buy this upgrade!");
                        }
                        else {
                            messageText.setText("Already arrived at garage,\nno more tow trucks required");
                        }
                    }
                break;
                case "Mechanic":
                    if(clickCount == mechanicPrice && stage == 1){
                        if (button3ClickIteration == 0) {
                            button3.setText("Car is 0% fixed");
                            clickCountLabel.setText("0m");
                        }
                        clicksLeftToFixCar--;
                        price4.setText(clicksLeftToFixCar + "m");
                        messageText.setText("You arrived at the mechanic! Phew!\nSpend 3500m + 500 clicks to\nrepair your wheels!");
                        repairCounter++;
                        repairCounterPercent = (float) repairCounter / CLICKS_TO_FIX_CAR * 100;
                        String repairPercentString = String.format("%.2f", repairCounterPercent);
                        button3.setText(repairPercentString + "% fixed");
//                        repairCounterPercent = 100; //DEBUG LINE - repairCounterPercent = 100; for debug delete for normal
                        if ((int)repairCounterPercent == 100) {
                            stage = 0;
                            carInMechanic = false;
                            clickCount=0;
                            button3.setText("Car Fixed!");
                            BeginningOfADrivingStage = true;
                            clickCountLabel.setText("0m");
                            temporarilyLockButtonsForStageAdvance(2);
                        }
                        button3ClickIteration++;
                    }
                    else {
                        if (!mechanicTriggeredYet && stage == 1) {
                            messageText.setText("\n\nYou need " + (mechanicPrice - clickCount) + " more metres\nto buy this upgrade!");
                        }
                        else{
                            messageText.setText("\nGarage Closed\nCar already repaired!");
                        }
                    }
                break;
                case "AdvanceStage":
                    stage = 0; //DEBUG
                    repairCounterPercent = 100;
                    if (repairCounterPercent == 100 && stage == 0) {
                        atStartEngineScreen = false;
                        Utils.playSound("src\\main\\resources\\com\\fsclicker\\sounds\\startCar.mp3");
                        letsDrive();
                    } else if (stage == 2) {
                        atStartEngineScreen = false;
                        Utils.playSound("src\\main\\resources\\com\\fsclicker\\sounds\\startCar.mp3");
                        letsEscapeByAir();
                    } else if (stage == 3) {
                        atStartEngineScreen = false;
                        Utils.playSound("src\\main\\resources\\com\\fsclicker\\sounds\\startJet.mp3");
                        try {
                            letsFly();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                break;
                case "Accelerate":
                    if (numberOfActiveNitros == 0) {
                        originalCPS = clicksPerSecond;
                        hasIncreasedSpeedFromZeroOnCurrentStageFlag = true;
                        if (clicksPerSecond <= MAX_NORMAL_SPEED_OF_CAR_MINUS_1) {
                            clicksPerSecond++;
                        }
                        if (stage == 3) {
                            if ((clicksPerSecond <= MAX_OVERDRIVE_SPEED_OF_CAR_MINUS_1 && overDrive)) {
                                clicksPerSecond++;
                            }
                        }
                        timerUpdate(nitroBeingUsed, thrustLevel);
                        if (stage == 2) {
                            speedRangeActual = (int) clicksPerSecond - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][0];
                            speedRangePermitted = (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][1] - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][0]);
                        } else if (stage == 3) {
                            speedRangeActual = (int) clicksPerSecond - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][0];
                            speedRangePermitted = (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][1] - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][0]);
                        }
                        if (countDownToPassObstacleOn && nextObstDistance > 0 && (speedRangeActual > speedRangePermitted || speedRangeActual < 0)) {
                            wasPassingNowFailing = true;
                        }
                        if (nextObstDistance < DISTANCE_ENTER_OBSTACLE_ELIGIBILITY && !countDownToPassObstacleOn && nextObstDistance >= 0 && (speedRangeActual <= speedRangePermitted && speedRangeActual >= 0)) {
                            startCountDownToPassObstacleFlag = true;
                            setCountDownToPassObstacleTimer();
                            countDownToPassObstacleTimerUpdate();
                        }
                        if (wasPassingNowFailing) {
                            //System.out.println("now failing, timer should reset");
                            countDownToPassObstacleOn = false;
                            startCountDownToPassObstacleFlag = false;
                            countDownToPassObstacleTimer.stop();
                            setCountDownToPassObstacleTimer();
                            countDownToPassObstacleTimerUpdate();
                            if (nextObstDistance < DISTANCE_ENTER_OBSTACLE_ELIGIBILITY && nextObstDistance >= 0 && (speedRangeActual <= speedRangePermitted && speedRangeActual >= 0)) {
                                //System.out.println("not failing any more timer should start again");
                                wasPassingNowFailing = false;
                                startCountDownToPassObstacleFlag = true;
                                setCountDownToPassObstacleTimer();
                                countDownToPassObstacleTimerUpdate();
                            }
                        }
                    }
                break;
                case "Brake":
                    if ((hasIncreasedSpeedFromZeroOnCurrentStageFlag && numberOfActiveNitros == 0) || adjustmentFlag) {
                        if (clicksPerSecond >= 1 && clicksPerSecond <= (MAX_OVERDRIVE_SPEED_OF_CAR_MINUS_1 + 1)) {
                            clicksPerSecond--;
                        } else if (clicksPerSecond < 1) {
                            clicksPerSecond = 0;
                        }
                        timerUpdate(nitroBeingUsed, thrustLevel);
                        if (stage == 2) {
                            speedRangeActual = (int) clicksPerSecond - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][0];
                            speedRangePermitted = (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][1] - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][0]);
                        } else if (stage == 3) {
                            speedRangeActual = (int) clicksPerSecond - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][0];
                            speedRangePermitted = (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][1] - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][0]);
                        }
                        if (countDownToPassObstacleOn && nextObstDistance > 0 && (speedRangeActual > speedRangePermitted || speedRangeActual < 0)) {
                            wasPassingNowFailing = true;
                        }
                        if (nextObstDistance < DISTANCE_ENTER_OBSTACLE_ELIGIBILITY && !countDownToPassObstacleOn && nextObstDistance >= 0 && (speedRangeActual <= speedRangePermitted && speedRangeActual >= 0)) {
                            startCountDownToPassObstacleFlag = true;
                            setCountDownToPassObstacleTimer();
                            countDownToPassObstacleTimerUpdate();
                        }
                        if (wasPassingNowFailing) {
                            //System.out.println("now failing, timer should reset");
                            countDownToPassObstacleOn = false;
                            startCountDownToPassObstacleFlag = false;
                            countDownToPassObstacleTimer.stop();
                            setCountDownToPassObstacleTimer();
                            countDownToPassObstacleTimerUpdate();
                            if (nextObstDistance < DISTANCE_ENTER_OBSTACLE_ELIGIBILITY && nextObstDistance >= 0 && (speedRangeActual <= speedRangePermitted && speedRangeActual >= 0)) {
                                //System.out.println("not failing any more timer should start again");
                                wasPassingNowFailing = false;
                                startCountDownToPassObstacleFlag = true;
                                setCountDownToPassObstacleTimer();
                                countDownToPassObstacleTimerUpdate();
                            }
                        }
                    } else if (stage == 3 && clicksPerSecond > 100 && nextObstDistance != (DISTANCE_DISPLAY_OBSTACLE_BOARD_STAGE3 + 1)) {
                        if (numberOfActiveNitros == 1) {
                            clicksPerSecond = originalCPS;
                            resetNitro();
                        } else {
                            clicksPerSecond = 100;
                            resetNitro();
                        }
                        timerUpdate(nitroBeingUsed, thrustLevel);
                    } else {
                        clicksPerSecond = 0;
                    }
                break;
                case "SwerveLeft":
                    //System.out.println("left");
                    if (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][2] == 1 && countDownToPassObstacleOn) { //if feature applicable to target and user in obstacle countdown
                        leftClickCount++;
                        passFailObstacle.setFont(font1);
                        passFailObstacle.setText("L: " + (requiredLeftClicks - leftClickCount) + " | R: " + (requiredRightClicks - rightClickCount));
                        //System.out.println("valid left clicks is" + leftClickCount);
                        if (leftClickCount > requiredLeftClicks || rightClickCount > requiredRightClicks) {
                            passFailObstacle.setFont(font3);
                            passFailObstacle.setText("CRASHED!");
                        }
                    }
                break;
                case "SwerveRight":
                    //System.out.println("right");
                    if (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][2] == 1 && countDownToPassObstacleOn) { //if feature applicable to target and user in obstacle countdown
                        rightClickCount++;
                        passFailObstacle.setFont(font1);
                        passFailObstacle.setText("L: " + (requiredLeftClicks - leftClickCount) + " | R: " + (requiredRightClicks - rightClickCount));
                        //System.out.println("valid right clicks is" + rightClickCount);
                        if (leftClickCount > requiredLeftClicks || rightClickCount > requiredRightClicks) {
                            passFailObstacle.setFont(font3);
                            passFailObstacle.setText("CRASHED!");
                        }
                    }
                break;
                case "OverDrive":
                    if (!overDrive && clicksPerSecond == OVERDRIVE_TOGGLE_SPEED) {
                        overDrive = true;
                        button3.setForeground(Color.red);
                        button3.setText("Overdrive: ON");
                        //play overdrive sound
                    } else if (overDrive && clicksPerSecond == OVERDRIVE_TOGGLE_SPEED){
                        overDrive = false;
                        button3.setForeground(Color.black);
                        button3.setText("Overdrive: OFF");
                    }
                break;
                case "Nitrous":
                    originalCPS = clicksPerSecond;
                    if(nitrousBoostsRemainingCount > 0 && numberOfActiveNitros < 5 && !nitroSpeedUp1 && !nitroSpeedUp2 && !nitroSpeedUp3 && !nitroSpeedUp4 && !nitroSpeedUp5 && overDrive) { //&& overDrive) {
                        nitroBeingUsed = boostInject();
                        timerUpdate(nitroBeingUsed, thrustLevel);
                    }
                break;
                case "RestartGame":
                    restartGame();
                break;
                case "AddNitros":
                    if (!timerAuxiliaryButtonDisappearFlag) {
                        nitrousBoostsRemainingCount = nitrousBoostsRemainingCount + NITROS_TO_ADD_WHEN_BUTTON_CLICKED;
                        nitroRechargeValue = generalTimerElapsedSeconds;
                        timerAuxiliaryButtonDisappearFlag = true;
                        timeUntilAuxiliaryButtonDisappears = generalTimerElapsedSeconds;
                        button4.setText("Super Nitro (" + nitrousBoostsRemainingCount + ")");
                    }
                break;
                case "GearUp":
                    if (!landingGearUpFlag && hasTookOff && clicksPerSecond <= 100) {
                        Utils.playSound("src\\main\\resources\\com\\fsclicker\\sounds\\gearUp.mp3");
                        landingGearUpFlag = true;
                        timerAuxiliaryButtonDisappearFlag = true;
                        timeUntilAuxiliaryButtonDisappears = generalTimerElapsedSeconds;
                        buttonAuxiliary.setBorder(BorderFactory.createEmptyBorder());
                    }
                break;
                case "RecoverStall":
                    speedDegrading = true;
                    stallActive = 0;
                    currentPitch = 5;
                    timerAuxiliaryButtonDisappearFlag = true;
                    timeUntilAuxiliaryButtonDisappears = generalTimerElapsedSeconds;
                    stallingWarningPanel.setVisible(false);
                    button1Unlocked = true;
                    button2Unlocked = true;
                    button3Unlocked = true;
                    button1.setActionCommand("ThrustUp");
                    button2.setActionCommand("ThrustDown");
                    button3.setActionCommand("PitchUp");
                    button4.setActionCommand("PitchDown");
                    button1.setText("Thrust +");
                    button2.setText("Thrust -");
                    button3.setText("Pitch Up");
                    button4.setText("Pitch Down");
                    button4.setForeground(Color.black);
                    button4.setFont(font1);
                    pitchInfo.setVisible(false);
                    pitchHUDDisplay.setImage(pitch5_level);
                    pitchInfo.setVisible(true);
                break;
                case "ThrustUp":
                        originalCPS = clicksPerSecond;
                        if (thrustLevel < 30 && thrustLevel >= 0) {
                            speedDegrading = false;
                            thrustLevel++;
                        }
                        if (stage4Start) {
                            setCountDownToPassObstacleTimer(); //redundant
                            countDownToPassObstacleTimer.stop(); //redundant
                            stage4Start = false;
                        }
                        if (clicksPerSecond == 0) {
                            hasIncreasedSpeedFromZeroOnCurrentStageFlag = true;
                            clicksPerSecond++;
                        }
                        timerUpdate(nitroBeingUsed, thrustLevel);
                        speedRangeActual = (int) clicksPerSecond - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG4[obstacleTarget][0];
                        speedRangePermitted = (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG4[obstacleTarget][1] - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG4[obstacleTarget][0]);

                        if (countDownToPassObstacleOn && nextObstDistance > 0 && (speedRangeActual > speedRangePermitted || speedRangeActual < 0)) {
                            wasPassingNowFailing = true;
                        }
                    if (nextObstDistance < DISTANCE_ENTER_OBSTACLE_ELIGIBILITY && nextObstDistance >= 0 && (speedRangeActual <= speedRangePermitted && speedRangeActual >= 0)) { // add extra stage 4 conditions if necessary when testing done
                        passObstacleFlag = 1;
                        obstaclePassed();
                    }
                    if (nextObstDistance < DISTANCE_ENTER_OBSTACLE_ELIGIBILITY && !hasTookOff && currentPitch < MIN_TAKEOFF_PITCH) {
                        passObstacleFlag = 2;
                        obstacleFailed();
                    }
                break;
                case "ThrustDown":
                    if ((hasIncreasedSpeedFromZeroOnCurrentStageFlag || adjustmentFlag)) {
                        if (thrustLevel > 0) {
                            thrustLevel--;
                            speedDegrading = true;
                        }
                        timerUpdate(nitroBeingUsed, thrustLevel);
                        speedRangeActual = (int) clicksPerSecond - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG4[obstacleTarget][0];
                        speedRangePermitted = (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG4[obstacleTarget][1] - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG4[obstacleTarget][0]);
                        if (countDownToPassObstacleOn && nextObstDistance > 0 && (speedRangeActual > speedRangePermitted || speedRangeActual < 0) && (altitudeRangeActual > altitudeRangePermitted || altitudeRangeActual < 0)) {
                            wasPassingNowFailing = true;
                        }
                        if (nextObstDistance < DISTANCE_ENTER_OBSTACLE_ELIGIBILITY && nextObstDistance >= 0 && (speedRangeActual <= speedRangePermitted && speedRangeActual >= 0)) { // add extra stage 4 conditions if necessary when testing done
                            passObstacleFlag = 1;
                            obstaclePassed();
                        }
                        if (nextObstDistance < DISTANCE_ENTER_OBSTACLE_ELIGIBILITY && !hasTookOff && currentPitch < MIN_TAKEOFF_PITCH) {
                            passObstacleFlag = 2;
                            obstacleFailed();
                        }
                    }
                break;
                case "PitchUp":
                    /**
                     * PITCH
                     * -----
                     *
                     * 11 settings - 0-10
                     *
                     * 0  - 60º (accelerate to max of thrust level plus 100), (descend by 133.34m for every 100m covered)
                     * 1  - 45º (accelerate to max of thrust level plus 75), (descend by 100m for every 100m covered)
                     * 2  - 30º (accelerate to max of thrust level plus 50), (descend by 66.67m for every 100m covered)
                     * 3  - 20º (accelerate to max of thrust level plus 33), (descend by 44.45m for every 100m covered)
                     * 4  - 10º (accelerate to max of thrust level plus 17), (descend by 22.22m for every 100m covered)
                     * 5  - 0 (level)
                     * 6  + 10º (degrade to 2/9 max for thrust level), (climb by 22.22m for every 100m covered)
                     * 7  + 20º (degrade to 4/9 max for thrust level), (climb by 44.45m for every 100m covered)
                     * 8  + 30º (degrade to 2/3 max for thrust level), (climb by 66.67m for every 100m covered)
                     * 9  + 45º (degrade to 1/2 max for thrust level), (climb by 100m for every 100m covered)
                     * 10 + 60º (degrade to 1/3 max for thrust level, (climb by 133.34m for every 100m covered)
                     *
                     * Stall
                     * -----
                     *
                     * If speed < STALL_LIMIT && hasTookOff, activate stall:
                     * - if altitude <= 0 && stallActive == true then gameOver()
                     * - lock all buttons
                     * - set thrust to level 10
                     * - pitch down to 60º (accelerate to max of thrust level 10 plus 100, descend by 133.34m for every 100m covered)
                     * - when max of thrust level 10 achieved, offer button with "Recover Stall":
                     * 	- sets pitch to 4 (level),
                     * 	- speed to degrade to max thrust level 10,
                     * 	- button disappears when clicked
                     * 	- unlock all buttons
                     *
                     * Rules of Altitude:
                     * ------------------
                     *
                     * - if altitude <= 0 && hasTookOff == true then gameOver()
                     * - show indicated airspeed (from array) and calculate stall based on this number
                     * - show ground speed as below, and calculate obstacle distances/end of stage distances based on that
                     * - if altitude:
                     * 	- 0-20000 - STALL_LIMIT= 100m/s indicated - GroundSpeed = Max Speed from Array
                     * 	- 0-25000 - STALL_LIMIT= 120m/s indicated - GroundSpeed = 115% max
                     * 	- 0-30000 - STALL_LIMIT= 150/s indicated - GroundSpeed = 125% max
                     * 	- 0-35000 - STALL_LIMIT= 200m/s indicated - GroundSpeed = 140% max
                     * 	- 0-40000 - STALL_LIMIT= 250m/s indicated - GroundSpeed = 160% max
                     * 	- This relationship between indicated and max airspeed should be evaluated every "tick" ie it is progressive and not with hard boundaries
                     */
                    if (hasTookOff || clicksPerSecond >= 80) {
                        if (!hasTookOff) {
                            if (currentPitch < 8) {
                                currentPitch++;
                            }
                        }
                        if (currentPitch < 10 && hasTookOff) {
                            currentPitch++;
                        }
                    }
                    if (hasTookOff) {
                        speedDegrading = true;
                    }
                    setPitchImage(currentPitch);
                break;
                case "PitchDown":
                    if (!hasTookOff) {
                        if (currentPitch > 5) {
                            currentPitch--;
                        }
                    }
                    if (hasTookOff) {
                        if (currentPitch > 0) {
                            currentPitch--;
                        }
                    }
                    setPitchImage(currentPitch);
                break;

            }
        }
    }

    private void setPitchImage(int currentPitch) {
        pitchInfo.setVisible(false);
        switch (currentPitch) {
            case 0 -> pitchHUDDisplay.setImage(pitch0);
            case 1 -> pitchHUDDisplay.setImage(pitch1);
            case 2 -> pitchHUDDisplay.setImage(pitch2);
            case 3 -> pitchHUDDisplay.setImage(pitch3);
            case 4 -> pitchHUDDisplay.setImage(pitch4);
            case 5 -> pitchHUDDisplay.setImage(pitch5_level);
            case 6 -> pitchHUDDisplay.setImage(pitch6);
            case 7 -> pitchHUDDisplay.setImage(pitch7);
            case 8 -> pitchHUDDisplay.setImage(pitch8);
            case 9 -> pitchHUDDisplay.setImage(pitch9);
            case 10 -> pitchHUDDisplay.setImage(pitch10);
        }
        pitchInfo.setVisible(true);
    }

    private void temporarilyLockButtonsForStageAdvance(int toggle) {
        if (toggle == 1) {
            button1.setText(LOCKED);
            button2.setText(LOCKED);
            button3.setText("Fix Your Car!");
            button1Unlocked = false;
            button2Unlocked = false;
            button3Unlocked = true;
            mechanicTriggeredYet = true;
        }
        else if (toggle == 2) {
            if (stage != 2 && stage != 3 && stage != 4) {
                button1Unlocked = true;
                button2Unlocked = true;
                button3Unlocked = false;
                driveUnlocked = true;
            } else {
                button1Unlocked = false;
                button2Unlocked = false;
                button3Unlocked = false;
                button1.setText(LOCKED);
                button2.setText(LOCKED);
                button3.setText(LOCKED);
                button1.setActionCommand("");
                button2.setActionCommand("");
                button3.setActionCommand("");
            }
            if ((stage == 0) || (stage == 2) || (stage == 3) || (stage == 4 && stallActive == 0)) {
                button4.setActionCommand("AdvanceStage");
            } else if (stage == 4) {
                button1Unlocked = true;
                button1.setActionCommand("");
                button2.setActionCommand("");
                button3.setActionCommand("");
                button4.setActionCommand("");
                button4.setText("STALLING!");
            }
            button4.setFont(font3);
            button4.setForeground(Color.red);
            button4.setBorder(raisedBorder);
            if (stage == 4 && stallActive == 0) {
                button4.setText("START SWIMMING!!");
                driveFirstClickFlag = 0;
                atStartEngineScreen = true;
            } else if (stallActive == 0) {
                button4.setText("START ENGINE!");
                driveFirstClickFlag = 0;
                atStartEngineScreen = true;
            }
            if (stage == 2) {
                generalTimerElapsedLabel.setText("You met Steve!");
                generalTimerElapsedValue.setText("NICE ONE MATE!");
            } else if(stage == 3) {
                generalTimerElapsedLabel.setText("You caught the plane!");
                generalTimerElapsedValue.setText("HERE'S TO LIFE!!");
            } else if(stage == 4 && stallActive == 0) {
                generalTimerElapsedLabel.setText("You landed safely!");
                generalTimerElapsedValue.setText("SWIM FOR IT!!");
            }
        }
    }
    public class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JButton button = (JButton) e.getSource();

            if (stage != 1 && driveFirstClickFlag == 0 && button == button4) {
                driveFirstClickFlag = 1;
                BeginningOfADrivingStage = false;
            }
            if ((stage == 0 || stage == 2 || stage == 3 || stage == 4)) {
                if ((stage == 2 || stage == 3) && button == button1 && !atStartEngineScreen) {
                    messageText.setText("\nAccelerate by 1m/s");
                } else if (stage == 4 && button == button1) {
                    messageText.setText("\n\n\nIncrease Thrust:\n " + thrustLevel + "/30");
                } else if (button == button1) {
                    messageText.setText("\n\n\nThis item is currently locked!");
                }
                if (stage == 2 && button == button2 && !atStartEngineScreen) {
                    messageText.setText("\nSlow by 1m/s");
                }  else if (stage == 4 && button == button2) {
                    messageText.setText("\n\n\nDecrease Thrust:\n " + thrustLevel + "/30");
                }  else if (stage == 3 && button == button2 && !atStartEngineScreen) {
                    messageText.setText("\nSlow by 1m/s\nOr if nitros are active, kill them!");
                } else if (button == button2 && atStartEngineScreen) {
                    messageText.setText("\n\n\nThis item is currently locked!");
                }
                if (button == button3 && stage == 2 && !atStartEngineScreen) {
                    messageText.setText("\nSwerve Left!");
                }  else if (stage == 4 && button == button3) {
                    currentPitchToDisplay = (int) PITCH_ARRAY[currentPitch][1];
                    messageText.setText("\n\n\nPitch Up: " + DEGREES_ARRAY[currentPitch]);
                }  else if (button == button3 && stage == 3 && !atStartEngineScreen) {
                    messageText.setText("Toggle OverDrive\nMust be at 70+m/s\nAllows further acceleration & nitros!");
                } else if (button == button3 && atStartEngineScreen) {
                    messageText.setText("\n\n\nThis item is currently locked!");
                }
                if (button == button4 && stage == 2 && !atStartEngineScreen) {
                    messageText.setText("\nSwerve Right!");
                } else if (stage == 4 && button == button4) {
                    currentPitchToDisplay = (int) PITCH_ARRAY[currentPitch][1];
                    messageText.setText("\n\n\nPitch Down: " + DEGREES_ARRAY[currentPitch]);
                } else if (button == button4 && stage == 3 && !atStartEngineScreen) {
                    messageText.setText("Nitrous Oxide\nHuge speed boost!\nYou only have " + nitrousBoostsRemainingCount + "!" + "\nAnd OverDrive needs to be on!");
                } else if (button == button4 && atStartEngineScreen && stage == 0) {
                    messageText.setText("\n\nStart your engine and\nbegin stage 2");
                } else if (button == button4 && atStartEngineScreen && stage == 2) {
                    messageText.setText("\n\nStart your engine and\nbegin stage 3");
                } else if (button == button4 && atStartEngineScreen && stage == 3) {
                    messageText.setText("\n\nStart your engine and\nbegin stage 4");
                } else if (button == buttonAuxiliary && stage == 4 && !landingGearUpFlag) {
                    messageText.setText("After Takeoff, click to raise landing gear to safely surpass 100m/s");
                } else if (button == buttonAuxiliary && stage == 4 && dropZoneFlag) {
                    messageText.setText("Click to drop the stash in the dropzone");
                } else if (button == buttonAuxiliary && stage == 4 && parachuteZoneFlag) {
                    messageText.setText("Click to jump out of the plane!");
                } else if (button == buttonAuxiliary && stage == 4 && hasTookOff && stallActive == 2) {
                    messageText.setText("Click to Recover from Stall!");
                }
            }

            if (gameOverFlag) {
                if (button == button1) {
                    messageText.setText("\n\n\nThis item is currently locked!");
                }
                if (button == button2) {
                    messageText.setText("\n\n\nThis item is currently locked!");
                }
                if (button == button3) {
                    messageText.setText("\n\n\nThis item is currently locked!");
                }
                if (button == button4) {
                    messageText.setText("\n\n\nClick to Restart Game");
                }
                if (button == buttonAuxiliary) {
                    messageText.setText("\n\n\nThis item is currently locked!");
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

            }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JButton button = (JButton) e.getSource();

            if (stage == 1) {
                if (button == button1) {
                    if ((!button1Unlocked) || (atStartEngineScreen)) {
                        messageText.setText("\n\n\nThis item is currently locked!");
                    } else {
                        messageText.setText("Hired Help!\n[Price: " + autoClickerPrice + "]\nExtra manpower to push \nevery 10 seconds!");
                    }
                } else if (button == button2) {
                    if ((!button2Unlocked) || (atStartEngineScreen)) {
                        messageText.setText("\n\n\nThis item is currently locked!");
                    } else {
                        messageText.setText("Call a Tow Truck!\n[Price: " + towTruckPrice + "]\nTow Truck will move you much\nfaster than a pair of hands!");
                    }

                } else if (button == button3) {
                    if ((!button3Unlocked) || (atStartEngineScreen)) {
                        messageText.setText("\n\n\nThis item is currently locked!");
                    } else {
                        messageText.setText("You arrived at the mechanic! Phew!\nSpend " + PRICE_TO_UNLOCK_MECHANIC + " + " + CLICKS_TO_FIX_CAR + " clicks to\nrepair your wheels!");
                    }
                } else if (button == button4) {
                    messageText.setText("\n\n\nThis item is currently locked!");
                }
            }
            if ((stage == 2 || stage == 3 || stage == 4) && !atStartEngineScreen) {
                if (button == buttonAuxiliary && stage == 3) {
                    messageText.setText("\n\n\nAdd " + NITROS_TO_ADD_WHEN_BUTTON_CLICKED + " Nitros!");
                }
                if (button == buttonAuxiliary && stage == 4 && hasTookOff && stallActive == 2) {
                    messageText.setText("Click to Recover from Stall!");
                }
                if (button == buttonAuxiliary && stage == 4 && !landingGearUpFlag) {
                    messageText.setText("After Takeoff, click to raise landing gear to safely surpass 100m/s");
                }
                if (button == buttonAuxiliary && stage == 4 && dropZoneFlag) {
                    messageText.setText("Click to drop the stash in the dropzone");
                }
                if (button == buttonAuxiliary && stage == 4 && parachuteZoneFlag) {
                    messageText.setText("Click to jump out of the plane!");
                }
                if ((stage == 2 || stage == 3) && button == button1) {
                    messageText.setText("\nAccelerate by 1m/s");
                } else if (stage == 4 && button == button1) {
                    messageText.setText("\n\n\nIncrease Thrust:\n " + thrustLevel + "/30");
                }
                if (stage == 2 && button == button2) {
                    messageText.setText("\nSlow by 1m/s");
                } else if (stage == 4 && button == button2) {
                    messageText.setText("\n\n\nDecrease Thrust:\n " + thrustLevel + "/30");
                }
                if (stage == 3 && button == button2 && driveFirstClickFlag != 0) {
                    messageText.setText("\nSlow by 1m/s\nOr if nitros are active, kill them!");
                }
                if (button == button3 && stage == 2) {
                    messageText.setText("\nSwerve Left!");
                } else if (button == button3 && stage == 3) {
                    messageText.setText("Toggle OverDrive\nMust be at 70+m/s\nAllows further acceleration & nitros!");
                } else if (stage == 4 && button == button3) {
                    currentPitchToDisplay = (int) PITCH_ARRAY[currentPitch][1];
                    messageText.setText("\n\n\nPitch Up: " + DEGREES_ARRAY[currentPitch]);
                }
                if (button == button4 && stage == 2) {
                    messageText.setText("\nSwerve Right!");
                } else if (button == button4 && stage == 3) {
                    messageText.setText("Nitrous Oxide\nHuge speed boost!\nYou only have " + nitrousBoostsRemainingCount + "!" + "\nAnd OverDrive needs to be on!");
                } else if (stage == 4 && button == button4) {
                    currentPitchToDisplay = (int) PITCH_ARRAY[currentPitch][1];
                    messageText.setText("\n\n\nPitch Down: " + DEGREES_ARRAY[currentPitch]);
                }
            } else if ((stage == 2 || stage == 3 || stage == 4) && (button == button1 || button == button2 || button == button3)) {
                messageText.setText("\n\n\nThis item is currently locked!");
            } else if ((stage == 0 && button == button1)) {
                messageText.setText("\n\n\nThis item is currently locked!");
            } else if ((stage == 0 && button == button2)) {
                messageText.setText("\n\n\nThis item is currently locked!");
            } else if ((stage == 0 && button == button3)) {
                messageText.setText("\nGarage Closed\nCar already repaired!");
            } else if ((stage == 0 && button == button4)) {
                    messageText.setText("\n\nStart your engine and\nbegin stage 2");
            } else if (((stage == 2) && button == button4)) {
                messageText.setText("\n\nStart your engine and\nbegin stage 3");
            } else if (((stage == 3) && button == button4)) {
                messageText.setText("\n\nStart your engine and\nbegin stage 4");
            } else if (((stage == 4) && button == button4)) {
                messageText.setText("\n\nStart Swimming to the stash!");
            }
            if (gameOverFlag) {
                if (button == button1) {
                    messageText.setText("\n\n\nThis item is currently locked!");
                }
                if (button == button2) {
                    messageText.setText("\n\n\nThis item is currently locked!");
                }
                if (button == button3) {
                    messageText.setText("\n\n\nThis item is currently locked!");
                }
                if (button == button4) {
                    messageText.setText("\n\n\nClick to Restart Game");
                }
                if (button == buttonAuxiliary) {
                    messageText.setText("\n\n\nThis item is currently locked!");
                }
            }
        }
        @Override
        public void mouseExited(MouseEvent e) {
            JButton button = (JButton) e.getSource();

            if (button == buttonAuxiliary) {
                messageText.setText(null);
            } else if(button == button1){
                messageText.setText(null);
            } else if (button == button2) {
                messageText.setText(null);
            } else if (button == button3) {
                messageText.setText(null);
            } else if (button == button4) {
                messageText.setText(null);
            }
        }
    }

    /**--------------------INITIALIZATION METHODS (DON'T EFFECT GAMEPLAY BEYOND START STAGE)----------------*/

    public void restartGame() {
        setInitialVariables(true); //set initial variables and reset everything if restarting
    }

    private void setInitialVariables(boolean restart) {
        if (restart) {
            altitudeZoneChangedFlag = false; planeBrokeFlag = false; overSpeedFlag = false; rippedGearOff = false; speedDegrading = false; hasTookOff = false; parachuteActive =false; stage4Start = false; landingGearUpFlag = false; dropZoneFlag = false; parachuteZoneFlag = false; approachFlag = false; adjustmentFlag = false; gameOverFlag = false; timerAuxiliaryButtonDisappearFlag = false; BeginningOfADrivingStage = false; timerOn = false; delayObstaclePanelOn = false; button1Unlocked = false; button2Unlocked = false; mechanicTriggeredYet = false; displayObstacleConditionsFlag = false;
            failedObstacleWithinApproachOfEndOfStageGoal = false; approachingEndOfStageGoalFlag = false; checkTimeGoalMoveCount = false; startDelayTimerFlag = false; countDownToPassObstacleOn = false; startCountDownToPassObstacleFlag = false; wasPassingNowFailing = false; moreThanOneMinuteElapsedFlag = false; button3Unlocked = false; driveUnlocked = false; carInMechanic = false; hasIncreasedSpeedFromZeroOnCurrentStageFlag = false;
            degradeNitro1 = false; degradeNitro2 = false; degradeNitro3 = false; degradeNitro4 = false; degradeNitro5 = false; nitro1 = false; nitro2 = false; nitro3 = false; nitro4 = false; nitro5 = false; nitroSpeedUp1 = false; nitroSpeedUp2 = false; nitroSpeedUp3 = false; nitroSpeedUp4 = false; nitroSpeedUp5 = false; overDrive = false; atStartEngineScreen = false; costOfFailureFirstIterationFlag = false;
            try {
                timer.restart();
                timer.stop();
            } catch (Exception e) {
                System.out.println("timer didn't exist.");
            }
            try {
                delayPanelAfterObstacleTimer.restart();
                delayPanelAfterObstacleTimer.stop();
            } catch (Exception e) {
                System.out.println("delay timer to remove obstacle timer didn't exist.");
            }
            try {
                countDownToPassObstacleTimer.restart();
                countDownToPassObstacleTimer.stop();
            } catch (Exception e) {
                System.out.println("countdown to pass obstacle timer didn't exist.");
            }
            try {
                generalElapsedCounter.restart();
                generalElapsedCounter.stop();
            } catch (Exception e) {
                System.out.println("general timer didn't exist.");
            }
        }
        altitudeAdjustedThrustArrayValue = 0;
        altitudeProportion = 0;
        originalOverSpeedTimerValue = 0;
        recoverySpeed = 0;
        newMaxSpeedAdjustedForPitch = 0;
        altitudeGain = 0;
        randomSubHundredFeetValue = 0;
        indAS = "";
        altString = "";
        indicatedAirspeedValue = 0;
        stallActive = 0;
        altitudeZone = 0;
        ripGearOffValue = 0;
        finalProportionPercentage = 0;
        currentSpeedProportionOfMaxForThrustLevel = 0;
        accelerationAmount = 0;
        speedKnots = 0;
        altitude = 0;
        currentPitchToDisplay = 0;
        currentPitch = 5;
        altitudeRangeActual = 0;
        altitudeRangePermitted = -1;
        thrustLevel = 0;
        approachTrigger = 0;
        nextSuccessfulObstacleLeavesDistance = 0;
        timeUntilAuxiliaryButtonDisappears = 0;
        nitroRechargeValue = 0;
        originalCPS = 0;
        km = "";
        kmGoal = "";
        clicksPerSecond = 0;
        clickCount = 0;
        autoClickerNumber = 0;
        autoClickerPrice = PRICE_TO_UNLOCK_HIRED_HELP;
        towTruckNumber = 0;
        towTruckPrice = PRICE_TO_UNLOCK_TOW_TRUCK;
        mechanicPrice = PRICE_TO_UNLOCK_MECHANIC;
        repairCounter = 0;
        clicksLeftToFixCar = CLICKS_TO_FIX_CAR;
        stage = 1;
        driveFirstClickFlag = 0;
        obstacleType = "Garage Gate";
        nextObstDistance = FIRST_OBSTACLE_STAGE2;
        distanceToEndOfStageGoal = 0;
        randomGoalMovementModifier = 0;
        obstacleTarget = 0;
        costOfFailureValue = 0;
        originalTimerObstacleValue = (int) ((Math.random() * (MAX_NEW_TIMER_AMOUNT - MIN_NEW_TIMER_AMOUNT)) + MIN_NEW_TIMER_AMOUNT);
        timerObstacleValue = originalTimerObstacleValue;
        passObstacleFlag = 0; //1 pass obst 2 fail obst
        speedRangePermitted = 0;
        speedRangeActual = 0;
        generalTimerElapsedMinutes = 0;
        generalTimerElapsedSeconds = 0;
        generalTimerSecondsToDisplay = 0;
        countTimesPassTimeUntilEndOStageGoal = 1;
        leftClickCount = 0;
        rightClickCount = 0;
        requiredLeftClicks = 0;
        requiredRightClicks = 0;
        countDisplay = 0;
        nitrousBoostsRemainingCount = 0;
        resetNitro();
        if (restart) {
            clickHere.setVisible(false);
            mainImageDisplay.setImage(mainDisplayImage);
            clickHere.setVisible(true);
            pitchInfo.setVisible(false);
            flightLevelLabel.setVisible(false);
            indicatedAirspeed.setVisible(false);
            generalTimerElapsedLabel.setForeground(Color.yellow);
            generalTimerElapsedLabel.setFont(font2);
            generalTimerElapsedLabel.setText("");
            generalTimerElapsedValue.setForeground(Color.white);
            generalTimerElapsedValue.setFont(font1);
            generalTimerElapsedValue.setText("");
            metresTravelledLabel.setText("Distance travelled:");
            metresTravelledLabel.setForeground(Color.yellow);
            metresTravelledLabel.setFont(font2);
            clickCountLabel.setForeground(Color.white);
            clickCountLabel.setFont(font1);
            clickCountLabel.setText("0m");
            perSecondLabelLabel.setText("");
            perSecondLabelLabel.setForeground(Color.yellow);
            perSecondLabelLabel.setFont(font2);
            perSecondLabel.setText("");
            perSecondLabel.setForeground(Color.white);
            perSecondLabel.setFont(font2);
            obstacleConditionsTitle.setText("");
            obstacleConditionsTitle.setForeground(Color.yellow);
            obstacleConditionsTitle.setFont(font2);
            obstacleConditions.setText("");
            obstacleConditions.setForeground(Color.white);
            obstacleConditions.setFont(font2);
            timerObstacleTitle.setText("");
            timerObstacleTitle.setForeground(Color.yellow);
            timerObstacleTitle.setFont(font2);
            timerObstacle.setText("");
            timerObstacle.setForeground(Color.white);
            timerObstacle.setFont(font2);
            costOfFailure.setText("");
            costOfFailure.setForeground(Color.red);
            costOfFailure.setFont(font2);
            passFailObstacle.setText("");
            passFailObstacle.setForeground(Color.green);
            passFailObstacle.setFont(font1);
            price.setText("Price");
            price.setHorizontalAlignment(JLabel.RIGHT);
            price.setForeground(Color.white);
            price.setFont(font1);
            price1.setText("10m");
            price1.setHorizontalAlignment(JLabel.RIGHT);
            price1.setForeground(Color.white);
            price1.setFont(font1);
            price2.setText("");
            price2.setHorizontalAlignment(JLabel.RIGHT);
            price2.setForeground(Color.white);
            price2.setFont(font1);
            price3.setText("");
            price3.setHorizontalAlignment(JLabel.RIGHT);
            price3.setForeground(Color.white);
            price3.setFont(font1);
            price4.setText("");
            price4.setHorizontalAlignment(JLabel.RIGHT);
            price4.setForeground(Color.white);
            price4.setFont(font1);
            button1.setText(LOCKED);
            button1.setFont(font1);
            button1.setActionCommand("Hired Help");
            button1.setForeground(Color.black);
            button2.setText(LOCKED);
            button2.setFont(font1);
            button2.setActionCommand("Tow Truck");
            button2.setForeground(Color.black);
            button3.setText(LOCKED);
            button3.setFont(font1);
            button3.setForeground(Color.black);
            button3.setActionCommand("Mechanic");
            button4.setText(LOCKED);
            button4.setFont(font1);
            button4.setForeground(Color.black);
            button4.setActionCommand(null);
            buttonAuxiliary.setVisible(false);
            distanceToGoTitleLabel.setText("");
            distanceToGoTitleLabel.setForeground(Color.yellow);
            distanceToGoTitleLabel.setFont(font2);
            distanceToGoLabel.setText("");
            distanceToGoLabel.setForeground(Color.white);
            distanceToGoLabel.setFont(font1);
            distanceToNextObstacleTitleLabel.setText("");
            distanceToNextObstacleTitleLabel.setForeground(Color.yellow);
            distanceToNextObstacleTitleLabel.setFont(font2);
            distanceToNextObstacleLabel.setText("");
            distanceToNextObstacleLabel.setForeground(Color.white);
            distanceToNextObstacleLabel.setFont(font1);
            whatIsNextObstacle.setText("");
            whatIsNextObstacle.setForeground(Color.white);
            whatIsNextObstacle.setFont(font2);
        }
    }
    private void resetNitro() {
        preNitroCPS1 = 0;
        preNitroCPS2 = 0;
        preNitroCPS3 = 0;
        preNitroCPS4 = 0;
        preNitroCPS5 = 0;
        originalGeneralTimerValue1 = 0;
        originalGeneralTimerValue2 = 0;
        originalGeneralTimerValue3 = 0;
        originalGeneralTimerValue4 = 0;
        originalGeneralTimerValue5 = 0;
        nitroTicks1 = 0;
        nitroTicks2 = 0;
        nitroTicks3 = 0;
        nitroTicks4 = 0;
        nitroTicks5 = 0;
        degradeNitro1 = false;
        degradeNitro2 = false;
        degradeNitro3 = false;
        degradeNitro4 = false;
        degradeNitro5 = false;
        nitro1 = false;
        nitro2 = false;
        nitro3 = false;
        nitro4 = false;
        nitro5 = false;
        nitroSpeedUp1 = false;
        nitroSpeedUp2 = false;
        nitroSpeedUp3 = false;
        nitroSpeedUp4 = false;
        nitroSpeedUp5 = false;
        nitrosArray[0] = false;
        nitrosArray[1] = false;
        nitrosArray[2] = false;
        nitrosArray[3] = false;
        nitrosArray[4] = false;
        nitroBeingUsed = 0;
        numberOfActiveNitros = 0;
    }
    private int boostInject() {
        int nitroNumberToBeUsed = 0;
        if (stage == 3 && clicksPerSecond <= MAX_SPEED_BOOST_CAN_BE_USED) {
            if (numberOfActiveNitros < MAX_NUMBER_OF_SIMULTANEOUS_NITROS) { // //add back in and refactor if needed for 5 to stop rapid clicking of Nitro
                int arrayElementCount = 0;
                numberOfActiveNitros++;
                for (int i = 0; i < nitrosArray.length; i++) {
                    arrayElementCount++;
                    if (!nitrosArray[i]) { //if this nitro is not being used
                        nitroNumberToBeUsed = arrayElementCount;
                        System.out.println("Nitro to be used is number: " + nitroNumberToBeUsed);
                        nitrosArray[i] = true;
                        break;
                    }
                }
                if (nitroNumberToBeUsed == 1) {
                    preNitroCPS1 = clicksPerSecond;
                    nitroTicks1 = 0;
                    originalGeneralTimerValue1 = 0;
                    nitroSpeedUp1 = true;
                }
                if (nitroNumberToBeUsed == 2) {
                    preNitroCPS2 = clicksPerSecond;
                    nitroTicks2 = 0;
                    originalGeneralTimerValue2 = 0;
                    nitroSpeedUp2 = true;
                }
                if (nitroNumberToBeUsed == 3) {
                    preNitroCPS3 = clicksPerSecond;
                    nitroTicks3 = 0;
                    originalGeneralTimerValue3 = 0;
                    nitroSpeedUp3 = true;
                }
                if (nitroNumberToBeUsed == 4) {
                    preNitroCPS4 = clicksPerSecond;
                    nitroTicks4 = 0;
                    originalGeneralTimerValue4 = 0;
                    nitroSpeedUp4 = true;
                }
                if (nitroNumberToBeUsed == 5) {
                    preNitroCPS5 = clicksPerSecond;
                    nitroTicks5 = 0;
                    originalGeneralTimerValue5 = 0;
                    nitroSpeedUp5 = true;
                }
                nitrousBoostsRemainingCount--;
                button4.setText("Super Nitro (" + nitrousBoostsRemainingCount + ")");
                System.out.println("Number of active nitros: " + numberOfActiveNitros);
            }
        }
        return nitroNumberToBeUsed;
    }

    private void letsDrive() {
        distanceToEndOfStageGoal = DISTANCE_TO_STEVE;
        repairCounterPercent = 0;
        double distanceToEndOfStageGoalAsDouble = distanceToEndOfStageGoal;
        DecimalFormat num = new DecimalFormat("0.00");
        num.setRoundingMode(RoundingMode.FLOOR);
        kmGoal = num.format(distanceToEndOfStageGoalAsDouble/1000);
        clicksPerSecond = 0;
        stage = 2;
        setGeneralTimer();
        generalElapsedCounter.start();
        setDelayPanelAfterObstacleTimer();
        distanceToGoTitleLabel.setText("Distance to Steve:");
        distanceToGoLabel.setText(kmGoal + "km");
        whatIsNextObstacle.setText("Next obstacle: " + obstacleType);
        distanceToNextObstacleTitleLabel.setText("Distance to obstacle:");
        distanceToNextObstacleLabel.setText(FIRST_OBSTACLE_STAGE2 + "m");
        button1.setActionCommand("Accelerate");
        button2.setActionCommand("Brake");
        button3.setActionCommand("SwerveLeft");
        button4.setActionCommand("SwerveRight");
        button1.setText("Accelerate");
        button2.setText("Brake");
        button3.setText("Left");
        button4.setText("Right");
        button4.setBorder(null);
        button4.setFont(font1);
        button4.setForeground(Color.black);
        price1.setText("");
        price2.setText("");
        price3.setText("");
        price4.setText("");
        price.setText("");
    }

    private void letsEscapeByAir() {
        nitroRechargeValue = 0;
        obstacleType = obstacleNameArrayStg3[0];
        obstacleTarget = 0;
        failedObstacleWithinApproachOfEndOfStageGoal = false;
        approachingEndOfStageGoalFlag = false;
        distanceToEndOfStageGoal = DISTANCE_TO_ESCAPE_PLANE;
        distanceToEndOfStageGoalAfterFail = 0;
        costOfFailureValue = COST_OF_FAILURE_STAGE3_START_VALUE;
        passFailObstacle.setText("");
        obstacleConditionsTitle.setText("");
        obstacleConditions.setText("");
        timerObstacleTitle.setText("");
        timerObstacle.setText("");
        costOfFailure.setText("");
        whatIsNextObstacle.setText("");
        stage = 3;
        nitrousBoostsRemainingCount = NITROUS_COUNT;
        nextObstDistance = FIRST_OBSTACLE_STAGE3;
        generalTimerElapsedLabel.setText("Escape Plane Leaves In " + (TIME_UNTIL_PENALTY_STAGE3 / 60) + " min!");
        generalTimerElapsedValue.setForeground(Color.white);
        generalTimerElapsedValue.setText("0:00");
        clickCountLabel.setText("0m");
        distanceToGoTitleLabel.setForeground(Color.yellow);
        distanceToGoTitleLabel.setText("Distance to Escape Plane:");
        distanceToGoLabel.setText((DISTANCE_TO_ESCAPE_PLANE / 1000) + "km");
        whatIsNextObstacle.setText("Next obstacle: Motorway Entry Road");
        distanceToNextObstacleTitleLabel.setText("Distance to obstacle:");
        distanceToNextObstacleLabel.setText(FIRST_OBSTACLE_STAGE3 + "m");
        button1Unlocked = true; //unlock buttons for stg2part2
        button2Unlocked = true;
        button3Unlocked = true;
        button1.setActionCommand("Accelerate");
        button2.setActionCommand("Brake");
        button3.setActionCommand("OverDrive");
        button4.setActionCommand("Nitrous");
        button1.setText("Accelerate");
        button2.setText("Brake / Kill Nitro");
        button3.setText("Overdrive: OFF");
        button4.setFont(font1);
        button4.setForeground(Color.black);
        button4.setText("Super Nitro (" + nitrousBoostsRemainingCount + (")"));
        generalElapsedCounter.restart();
        generalElapsedCounter.start();
        moreThanOneMinuteElapsedFlag = false;
        generalTimerElapsedSeconds = 0;
        generalTimerElapsedMinutes = 0;
        generalTimerSecondsToDisplay = 0;
        leftClickCount = 0;
        rightClickCount = 0;
        clickCount = 0;
        System.out.println("You beat Stage 2 and met Steve!");
    }

    private void letsFly() throws IOException {
        speedLabel.setVisible(true);
        flightLevelLabel.setVisible(true);
        altString = String.format("%03d", (int) altitude);
        altitudeDisplayValue.setText(altString);
        altitudeHundredthsDisplayValue.setText("00");
        indicatedAirspeedValue = 0;
        indAS = String.format("%03d", 0);
        indicatedAirspeed.setText(indAS + "IAS");
        priceLabelPanel.setVisible(false);
        pricePanel.setVisible(false);
        pitchInfo.setVisible(true);
        ripGearOffValue = (int) ((Math.random() * (MAX_RIP_GEAR_OFF_DISTANCE - MIN_RIP_GEAR_OFF_DISTANCE)) + MIN_RIP_GEAR_OFF_DISTANCE);
        stage4Start = true;
        mainImageDisplay.setImage(jetImage);
        mainImageButton.setActionCommand(null);
        obstacleType = obstacleNameArrayStg4[0];
        obstacleTarget = 0;
        failedObstacleWithinApproachOfEndOfStageGoal = false;
        approachingEndOfStageGoalFlag = false;
        distanceToEndOfStageGoal = DISTANCE_TO_PARACHUTE_ZONE;
        distanceToEndOfStageGoalAfterFail = 0;
        costOfFailureValue = 0;
        price.setText("");
        price1.setText("");
        price2.setText("");
        price3.setText("");
        price4.setText("");
        passFailObstacle.setText("");
        obstacleConditionsTitle.setText("");
        obstacleConditions.setText("");
        timerObstacleTitle.setText("");
        timerObstacle.setText("");
        costOfFailure.setText("");
        whatIsNextObstacle.setText("");
        stage = 4;
        nextObstDistance = FIRST_OBSTACLE_STAGE4;
        generalTimerElapsedLabel.setText("Drop Zone secure for " + (TIME_UNTIL_PENALTY_STAGE4 / 60) + " min!");
        generalTimerElapsedValue.setForeground(Color.white);
        generalTimerElapsedValue.setText("0:00");
        clickCountLabel.setText("0m");
        distanceToGoTitleLabel.setForeground(Color.yellow);
        distanceToGoTitleLabel.setText("Distance to Parachute Zone:");
        distanceToGoLabel.setText((DISTANCE_TO_PARACHUTE_ZONE / 1000) + "km");
        whatIsNextObstacle.setText("Next obstacle: " + obstacleType);
        distanceToNextObstacleTitleLabel.setText("Distance to obstacle:");
        distanceToNextObstacleLabel.setText(FIRST_OBSTACLE_STAGE4 + "m");
        button1Unlocked = true;
        button2Unlocked = true;
        button3Unlocked = true;
        button1.setActionCommand("ThrustUp");
        button2.setActionCommand("ThrustDown");
        button3.setActionCommand("PitchUp");
        button4.setActionCommand("PitchDown");
        button1.setText("Thrust +");
        button2.setText("Thrust -");
        button3.setText("Pitch Up");
        button4.setFont(font1);
        button4.setForeground(Color.black);
        button4.setText("Pitch Down");
        buttonAuxiliary.setVisible(false);
        buttonAuxiliary.setText("GearUp");
        buttonAuxiliary.setActionCommand("GearUp");
//        setTimer(); // comment out for real, leave in for DEBUG
//        setGeneralTimer();// comment out for real, leave in for DEBUG
        generalElapsedCounter.restart();
        generalElapsedCounter.start();
        moreThanOneMinuteElapsedFlag = false;
        generalTimerElapsedSeconds = 0;
        generalTimerElapsedMinutes = 0;
        generalTimerSecondsToDisplay = 0;
        landingGearUpFlag = false;
        dropZoneFlag = false;
        parachuteZoneFlag = false;
        clickCount = 0;
        System.out.println("You beat Stage 3 and reached the plane!");
    }

    private void gameOver() {
        buttonAuxiliary.setVisible(false);
        generalTimerElapsedValue.setForeground(Color.red);
        if (stage == 2) {
            generalTimerElapsedValue.setText("YOU HIT STEVE!!");
        } else if (stage == 3 && nextObstDistance == 0){
            generalTimerElapsedValue.setText("PLANE SMASHED!");
        } else if (stage == 3) {
            generalTimerElapsedValue.setText("PLANE JLabel.LEFT!");
        } else if (stage == 4 && !landingGearUpFlag && !rippedGearOff) {
            generalTimerElapsedValue.setText("CRASHED ON TAKEOFF!");
        } else if (stage == 4 && !landingGearUpFlag) {
            generalTimerElapsedValue.setText("RIPPED GEAR OFF!");
        } else if (stage == 4 && parachuteActive && altitude == 0 && clicksPerSecond > 4) {
            generalTimerElapsedValue.setText("YOU DIED ON IMPACT!!");
        } else if (stage == 4 && stallActive == 2 && altitude == 0) {
            generalTimerElapsedValue.setText("STALLED AND CRASHED!");
        } else if (stage == 4 && hasTookOff && altitude <= 0) {
             generalTimerElapsedValue.setText("CRASHED INTO GROUND!");
        } else if (stage == 4 && planeBrokeFlag) {
            generalTimerElapsedValue.setText("PLANE BROKE UP!!");
        }
        altitudeHundredthsDisplayValue.setText("00");
        clicksPerSecond = 0;
        passFailObstacle.setForeground(Color.red);
        passFailObstacle.setFont(font3);
        passFailObstacle.setText("GAME OVER!");
        button1Unlocked = false;
        button2Unlocked = false;
        button3Unlocked = false;
        button1.setText(LOCKED);
        button2.setText(LOCKED);
        button3.setText(LOCKED);
        button3.setForeground(Color.black);
        button1.setActionCommand("");
        button2.setActionCommand("");
        button3.setActionCommand("");
        button4.setActionCommand("RestartGame");
        buttonAuxiliary.setActionCommand(null);
        button4.setText("Restart Game");
        System.out.println("Game Over");
        timer.stop();
        generalElapsedCounter.stop();
        gameOverFlag = true;
    }
}