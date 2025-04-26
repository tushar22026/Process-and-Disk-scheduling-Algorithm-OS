
package com.example;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class AlgorithmOSProj extends JFrame {
    Container c;
    Font f, f0;
    JLabel input, algo, arrival, burst, quantum, priority, diskRequests, headPosition, direction;
    JComboBox<String> box;
    JButton solve;
    JTextField atf, btf, qtf, priorityField, diskRequestsField, headPositionField;
    JComboBox<String> directionBox;
    JTextArea outputArea;
    String[] algorithms = {"FCFS", "SJF (Non-Preemptive)", "RR", "Priority (Non-Preemptive)",
            "Priority (Preemptive)", "FCFS (Disk)", "SSTF (Disk)",
            "LOOK (Disk)", "C-LOOK (Disk)", "SCAN (Disk)", "C-SCAN (Disk)"};

    AlgorithmOSProj() {
        Display();
    }

    public void Display() {
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(Color.lightGray);

        // Fonts
        f = new Font("Arial", Font.PLAIN, 18);
        f0 = new Font("Arial", 0, 18);

        // Input Label
        input = new JLabel("Input");
        input.setBounds(10, 10, 80, 30);
        input.setFont(f);
        c.add(input);

        // Algorithm Selection
        algo = new JLabel("Algorithm*");
        algo.setBounds(10, 40, 100, 30);
        algo.setFont(f0);
        c.add(algo);

        box = new JComboBox<>(algorithms);
        box.setBounds(10, 73, 250, 30);
        box.setFont(f0);
        c.add(box);

        // Arrival Time Input
        arrival = new JLabel("Arrival Times");
        arrival.setBounds(10, 100, 250, 30);
        arrival.setFont(f0);
        c.add(arrival);

        atf = new JTextField(10);
        atf.setBounds(10, 130, 250, 30);
        atf.setFont(f0);
        c.add(atf);

        // Burst Time Input
        burst = new JLabel("Burst Times");
        burst.setBounds(10, 160, 250, 30);
        burst.setFont(f0);
        c.add(burst);

        btf = new JTextField(10);
        btf.setBounds(10, 190, 250, 30);
        btf.setFont(f0);
        c.add(btf);

        // Quantum Time Input (Initially hidden)
        quantum = new JLabel("Quantum Time");
        quantum.setBounds(10, 220, 250, 30);
        quantum.setFont(f0);
        quantum.setVisible(false);
        c.add(quantum);

        qtf = new JTextField(10);
        qtf.setBounds(10, 250, 250, 30);
        qtf.setFont(f0);
        qtf.setVisible(false);
        c.add(qtf);

        // Priority Input (Initially hidden)
        priority = new JLabel("Priority Values");
        priority.setBounds(10, 280, 250, 30);
        priority.setFont(f0);
        priority.setVisible(false);
        c.add(priority);

        priorityField = new JTextField(10);
        priorityField.setBounds(10, 310, 250, 30);
        priorityField.setFont(f0);
        priorityField.setVisible(false);
        c.add(priorityField);

        // Disk Requests Input (Initially hidden)
        diskRequests = new JLabel("Disk Requests");
        diskRequests.setBounds(10, 100, 250, 30);
        diskRequests.setFont(f0);
        diskRequests.setVisible(false);
        c.add(diskRequests);

        diskRequestsField = new JTextField(10);
        diskRequestsField.setBounds(10, 130, 250, 30);
        diskRequestsField.setFont(f0);
        diskRequestsField.setVisible(false);
        c.add(diskRequestsField);

        // Head Position Input (Initially hidden)
        headPosition = new JLabel("Head Position");
        headPosition.setBounds(10, 160, 250, 30);
        headPosition.setFont(f0);
        headPosition.setVisible(false);
        c.add(headPosition);

        headPositionField = new JTextField(10);
        headPositionField.setBounds(10, 190, 250, 30);
        headPositionField.setFont(f0);
        headPositionField.setVisible(false);
        c.add(headPositionField);

        // Direction Input (Initially hidden)
        direction = new JLabel("Direction");
        direction.setBounds(10, 220, 250, 30);
        direction.setFont(f0);
        direction.setVisible(false);
        c.add(direction);

        String[] directions = {"Left", "Right"};
        directionBox = new JComboBox<>(directions);
        directionBox.setBounds(10, 250, 250, 30);
        directionBox.setFont(f0);
        directionBox.setVisible(false);
        c.add(directionBox);

        // Solve Button
        solve = new JButton("Solve");
        solve.setBounds(10, 350, 100, 30);
        solve.setFont(f0);
        solve.setBackground(Color.black);
        solve.setForeground(Color.lightGray);
        c.add(solve);

        // Output Area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(300, 10, 650, 500);
        c.add(scrollPane);

        // Action Listener for Solve Button
        solve.addActionListener(e -> calculateAlgorithm());

        // Change visibility of fields based on selected algorithm
        box.addActionListener(e -> {
            String selectedAlgorithm = (String) box.getSelectedItem();

            // Hide all fields first
            arrival.setVisible(false);
            atf.setVisible(false);
            burst.setVisible(false);
            btf.setVisible(false);
            quantum.setVisible(false);
            qtf.setVisible(false);
            priority.setVisible(false);
            priorityField.setVisible(false);
            diskRequests.setVisible(false);
            diskRequestsField.setVisible(false);
            headPosition.setVisible(false);
            headPositionField.setVisible(false);
            direction.setVisible(false);
            directionBox.setVisible(false);

            if (selectedAlgorithm.contains("(Disk)")) {
                // Disk scheduling algorithms
                diskRequests.setVisible(true);
                diskRequestsField.setVisible(true);
                headPosition.setVisible(true);
                headPositionField.setVisible(true);

                if (selectedAlgorithm.equals("LOOK (Disk)") ||
                        selectedAlgorithm.equals("C-LOOK (Disk)") ||
                        selectedAlgorithm.equals("SCAN (Disk)") ||
                        selectedAlgorithm.equals("C-SCAN (Disk)")) {
                    direction.setVisible(true);
                    directionBox.setVisible(true);
                }
            } else {
                // CPU scheduling algorithms
                arrival.setVisible(true);
                atf.setVisible(true);
                burst.setVisible(true);
                btf.setVisible(true);

                if ("RR".equals(selectedAlgorithm)) {
                    quantum.setVisible(true);
                    qtf.setVisible(true);
                } else if (selectedAlgorithm.contains("Priority")) {
                    priority.setVisible(true);
                    priorityField.setVisible(true);
                }
            }
        });
    }

    private void calculateAlgorithm() {
        String selectedAlgorithm = (String) box.getSelectedItem();

        if (selectedAlgorithm.contains("(Disk)")) {
            calculateDiskAlgorithm(selectedAlgorithm);
        } else {
            calculateCPUAlgorithm(selectedAlgorithm);
        }
    }

    private void calculateCPUAlgorithm(String selectedAlgorithm) {
        String arrivalTimesInput = atf.getText();
        String burstTimesInput = btf.getText();
        String quantumInput = qtf.getText();
        String priorityInput = priorityField.getText();

        try {
            // Parse arrival and burst times
            String[] arrivalTimesArr = arrivalTimesInput.split(",");
            String[] burstTimesArr = burstTimesInput.split(",");
            int n = arrivalTimesArr.length;

            if (n != burstTimesArr.length) {
                outputArea.setText("Error: Arrival and Burst times must have the same number of values.");
                return;
            }

            int[][] processes = new int[n][5]; // ID, Arrival Time, Burst Time, Remaining Burst Time, Priority
            for (int i = 0; i < n; i++) {
                processes[i][0] = i + 1; // Process ID
                processes[i][1] = Integer.parseInt(arrivalTimesArr[i].trim());
                processes[i][2] = Integer.parseInt(burstTimesArr[i].trim());
                processes[i][3] = processes[i][2]; // Remaining Burst Time initially equals burst time
                if (selectedAlgorithm.contains("Priority")) {
                    processes[i][4] = Integer.parseInt(priorityInput.split(",")[i].trim()); // Parse priority
                }
            }

            if ("FCFS".equals(selectedAlgorithm)) {
                String result = calculateFCFS(processes);
                outputArea.setText(result);
            } else if ("SJF (Non-Preemptive)".equals(selectedAlgorithm)) {
                String result = calculateSJFNonPreemptive(processes);
                outputArea.setText(result);
            } else if ("RR".equals(selectedAlgorithm)) {
                String result = calculateRR(processes, Integer.parseInt(quantumInput));
                outputArea.setText(result);
            } else if ("Priority (Non-Preemptive)".equals(selectedAlgorithm) || "Priority (Preemptive)".equals(selectedAlgorithm)) {
                String result = calculatePriority(processes, selectedAlgorithm);
                outputArea.setText(result);
            } else {
                outputArea.setText(selectedAlgorithm + " algorithm is not yet implemented.");
            }

        } catch (NumberFormatException ex) {
            outputArea.setText("Error: Please ensure all inputs are valid integers.");
        }
    }

    private void calculateDiskAlgorithm(String selectedAlgorithm) {
        String requestsInput = diskRequestsField.getText();
        String headPositionInput = headPositionField.getText();
        String directionInput = (String) directionBox.getSelectedItem();

        try {
            // Parse disk requests
            String[] requestsArr = requestsInput.split(",");
            int n = requestsArr.length;
            int[] requests = new int[n];

            for (int i = 0; i < n; i++) {
                requests[i] = Integer.parseInt(requestsArr[i].trim());
            }

            int head = Integer.parseInt(headPositionInput.trim());

            String result;
            switch (selectedAlgorithm) {
                case "FCFS (Disk)":
                    result = calculateFCFSDisk(requests, head);
                    break;
                case "SSTF (Disk)":
                    result = calculateSSTFDisk(requests, head);
                    break;
                case "LOOK (Disk)":
                    result = calculateLOOKDisk(requests, head, directionInput.equals("Left"));
                    break;
                case "C-LOOK (Disk)":
                    result = calculateCLOOKDisk(requests, head, directionInput.equals("Left"));
                    break;
                case "SCAN (Disk)":
                    result = calculateSCANDisk(requests, head, directionInput.equals("Left"));
                    break;
                case "C-SCAN (Disk)":
                    result = calculateCSCANDisk(requests, head, directionInput.equals("Left"));
                    break;
                default:
                    result = "Disk scheduling algorithm not implemented yet.";
            }

            outputArea.setText(result);

        } catch (NumberFormatException ex) {
            outputArea.setText("Error: Please ensure all inputs are valid integers.");
        }
    }

    private String calculateFCFS(int[][] processes) {
        int n = processes.length;
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] completionTime = new int[n];
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        int currentTime = 0;
        for (int i = 0; i < n; i++) {
            if (processes[i][1] > currentTime) {
                currentTime = processes[i][1];
            }
            currentTime += processes[i][2];
            completionTime[i] = currentTime;
            waitingTime[i] = completionTime[i] - processes[i][1] - processes[i][2];
            turnaroundTime[i] = completionTime[i] - processes[i][1];
        }

        for (int i = 0; i < n; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }

        StringBuilder result = new StringBuilder();
        result.append("Process   Arrival   Burst   Waiting   Turnaround\n");
        result.append("---------------------------------------------------\n");
        for (int i = 0; i < n; i++) {
            result.append(String.format("%-7d   %-7d   %-5d   %-7d   %-10d\n",
                    processes[i][0], processes[i][1], processes[i][2], waitingTime[i], turnaroundTime[i]));
        }

        result.append("\nAverage Waiting Time: " + (double) totalWaitingTime / n);
        result.append("\nAverage Turnaround Time: " + (double) totalTurnaroundTime / n);

        return result.toString();
    }

    private String calculateSJFNonPreemptive(int[][] processes) {
        int n = processes.length;
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] completionTime = new int[n];
        boolean[] completed = new boolean[n];
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        int currentTime = 0;
        int completedProcesses = 0;

        while (completedProcesses < n) {
            int shortestIndex = -1;
            int shortestBurst = Integer.MAX_VALUE;

            // Find the process with shortest burst time that has arrived and not completed
            for (int i = 0; i < n; i++) {
                if (!completed[i] && processes[i][1] <= currentTime && processes[i][2] < shortestBurst) {
                    shortestBurst = processes[i][2];
                    shortestIndex = i;
                }
            }

            if (shortestIndex == -1) {
                currentTime++;
                continue;
            }

            // Execute the shortest job
            currentTime += processes[shortestIndex][2];
            completionTime[shortestIndex] = currentTime;
            turnaroundTime[shortestIndex] = completionTime[shortestIndex] - processes[shortestIndex][1];
            waitingTime[shortestIndex] = turnaroundTime[shortestIndex] - processes[shortestIndex][2];
            completed[shortestIndex] = true;
            completedProcesses++;
        }

        // Calculate totals
        for (int i = 0; i < n; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }

        // Build result string
        StringBuilder result = new StringBuilder();
        result.append("Process   Arrival   Burst   Waiting   Turnaround\n");
        result.append("---------------------------------------------------\n");
        for (int i = 0; i < n; i++) {
            result.append(String.format("%-7d   %-7d   %-5d   %-7d   %-10d\n",
                    processes[i][0], processes[i][1], processes[i][2], waitingTime[i], turnaroundTime[i]));
        }

        result.append("\nAverage Waiting Time: ").append((double) totalWaitingTime / n);
        result.append("\nAverage Turnaround Time: ").append((double) totalTurnaroundTime / n);

        return result.toString();
    }

    private String calculateRR(int[][] processes, int quantum) {
        int n = processes.length;
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] remainingBurstTime = new int[n];
        int[] completionTime = new int[n];  // Added to track completion times
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        // Initialize remaining burst times
        for (int i = 0; i < n; i++) {
            remainingBurstTime[i] = processes[i][2];
        }

        int currentTime = 0;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] isCompleted = new boolean[n];
        int remainingProcesses = n;

        // Add all processes that arrive at time 0
        for (int i = 0; i < n; i++) {
            if (processes[i][1] <= currentTime) {
                queue.add(i);
            }
        }

        while (remainingProcesses > 0) {
            if (queue.isEmpty()) {
                currentTime++;
                // Check if any new processes arrived during this time
                for (int i = 0; i < n; i++) {
                    if (!isCompleted[i] && processes[i][1] <= currentTime && !queue.contains(i)) {
                        queue.add(i);
                    }
                }
            } else {
                int index = queue.poll();

                if (remainingBurstTime[index] > quantum) {
                    currentTime += quantum;
                    remainingBurstTime[index] -= quantum;
                } else {
                    currentTime += remainingBurstTime[index];
                    remainingBurstTime[index] = 0;
                    completionTime[index] = currentTime;
                    turnaroundTime[index] = completionTime[index] - processes[index][1];
                    waitingTime[index] = turnaroundTime[index] - processes[index][2];
                    isCompleted[index] = true;
                    remainingProcesses--;
                }

                // Check for newly arrived processes during the execution of current process
                for (int i = 0; i < n; i++) {
                    if (!isCompleted[i] && processes[i][1] > (currentTime - (remainingBurstTime[index] > 0 ? quantum : remainingBurstTime[index] + quantum))
                            && processes[i][1] <= currentTime && !queue.contains(i)) {
                        queue.add(i);
                    }
                }

                // Re-add the current process to queue if it's not completed
                if (remainingBurstTime[index] > 0 && !isCompleted[index]) {
                    queue.add(index);
                }
            }
        }

        // Calculate totals
        for (int i = 0; i < n; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }

        // Build result string
        StringBuilder result = new StringBuilder();
        result.append("Process   Arrival   Burst   Waiting   Turnaround\n");
        result.append("---------------------------------------------------\n");
        for (int i = 0; i < n; i++) {
            result.append(String.format("%-7d   %-7d   %-5d   %-7d   %-10d\n",
                    processes[i][0], processes[i][1], processes[i][2], waitingTime[i], turnaroundTime[i]));
        }

        result.append("\nAverage Waiting Time: ").append((double) totalWaitingTime / n);
        result.append("\nAverage Turnaround Time: ").append((double) totalTurnaroundTime / n);

        return result.toString();
    }

    private String calculatePriority(int[][] processes, String selectedAlgorithm) {
        int n = processes.length;
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] completionTime = new int[n];
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        Arrays.sort(processes, (a, b) -> Integer.compare(b[4], a[4]));

        int currentTime = 0;
        for (int i = 0; i < n; i++) {
            if (processes[i][1] > currentTime) {
                currentTime = processes[i][1];
            }
            currentTime += processes[i][2];
            completionTime[i] = currentTime;
            waitingTime[i] = completionTime[i] - processes[i][1] - processes[i][2];
            turnaroundTime[i] = completionTime[i] - processes[i][1];
        }

        for (int i = 0; i < n; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }

        StringBuilder result = new StringBuilder();
        result.append("Process   Arrival   Burst   Priority   Waiting   Turnaround\n");
        result.append("-------------------------------------------------------------\n");
        for (int i = 0; i < n; i++) {
            result.append(String.format("%-7d   %-7d   %-5d   %-8d   %-7d   %-10d\n",
                    processes[i][0], processes[i][1], processes[i][2], processes[i][4], waitingTime[i], turnaroundTime[i]));
        }

        result.append("\nAverage Waiting Time: " + (double) totalWaitingTime / n);
        result.append("\nAverage Turnaround Time: " + (double) totalTurnaroundTime / n);

        return result.toString();
    }

    private String calculateFCFSDisk(int[] requests, int head) {
        int totalSeekOperations = 0;
        int currentPosition = head;
        StringBuilder sequence = new StringBuilder();
        sequence.append("Head Movement Sequence: ").append(currentPosition);

        for (int request : requests) {
            totalSeekOperations += Math.abs(request - currentPosition);
            currentPosition = request;
            sequence.append(" -> ").append(currentPosition);
        }

        StringBuilder result = new StringBuilder();
        result.append("FCFS Disk Scheduling Algorithm\n");
        result.append("Initial Head Position: ").append(head).append("\n");
        result.append(sequence.toString()).append("\n");
        result.append("Total Seek Operations: ").append(totalSeekOperations).append("\n");
        result.append("Average Seek Length: ").append((double) totalSeekOperations / requests.length).append("\n");

        return result.toString();
    }

    private String calculateSSTFDisk(int[] requests, int head) {
        ArrayList<Integer> requestList = new ArrayList<>();
        for (int request : requests) {
            requestList.add(request);
        }

        int totalSeekOperations = 0;
        int currentPosition = head;
        StringBuilder sequence = new StringBuilder();
        sequence.append("Head Movement Sequence: ").append(currentPosition);

        while (!requestList.isEmpty()) {
            int closestRequest = findClosestRequest(requestList, currentPosition);
            totalSeekOperations += Math.abs(closestRequest - currentPosition);
            currentPosition = closestRequest;
            sequence.append(" -> ").append(currentPosition);
            requestList.remove(Integer.valueOf(closestRequest));
        }

        StringBuilder result = new StringBuilder();
        result.append("SSTF Disk Scheduling Algorithm\n");
        result.append("Initial Head Position: ").append(head).append("\n");
        result.append(sequence.toString()).append("\n");
        result.append("Total Seek Operations: ").append(totalSeekOperations).append("\n");
        result.append("Average Seek Length: ").append((double) totalSeekOperations / requests.length).append("\n");

        return result.toString();
    }

    private int findClosestRequest(ArrayList<Integer> requests, int currentPosition) {
        int closest = requests.get(0);
        int minDistance = Math.abs(closest - currentPosition);

        for (int request : requests) {
            int distance = Math.abs(request - currentPosition);
            if (distance < minDistance) {
                minDistance = distance;
                closest = request;
            }
        }

        return closest;
    }

    private String calculateLOOKDisk(int[] requests, int head, boolean leftDirection) {
        ArrayList<Integer> requestList = new ArrayList<>();
        for (int request : requests) {
            requestList.add(request);
        }

        Collections.sort(requestList);

        int totalSeekOperations = 0;
        int currentPosition = head;
        StringBuilder sequence = new StringBuilder();
        sequence.append("Head Movement Sequence: ").append(currentPosition);

        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        for (int request : requestList) {
            if (request < head) {
                left.add(request);
            } else {
                right.add(request);
            }
        }

        if (leftDirection) {
            Collections.reverse(left);
            for (int request : left) {
                totalSeekOperations += Math.abs(request - currentPosition);
                currentPosition = request;
                sequence.append(" -> ").append(currentPosition);
            }

            for (int request : right) {
                totalSeekOperations += Math.abs(request - currentPosition);
                currentPosition = request;
                sequence.append(" -> ").append(currentPosition);
            }
        } else {
            for (int request : right) {
                totalSeekOperations += Math.abs(request - currentPosition);
                currentPosition = request;
                sequence.append(" -> ").append(currentPosition);
            }

            Collections.reverse(left);
            for (int request : left) {
                totalSeekOperations += Math.abs(request - currentPosition);
                currentPosition = request;
                sequence.append(" -> ").append(currentPosition);
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("LOOK Disk Scheduling Algorithm\n");
        result.append("Initial Head Position: ").append(head).append("\n");
        result.append("Direction: ").append(leftDirection ? "Left" : "Right").append("\n");
        result.append(sequence.toString()).append("\n");
        result.append("Total Seek Operations: ").append(totalSeekOperations).append("\n");
        result.append("Average Seek Length: ").append((double) totalSeekOperations / requests.length).append("\n");

        return result.toString();
    }

    private String calculateCLOOKDisk(int[] requests, int head, boolean leftDirection) {
        ArrayList<Integer> requestList = new ArrayList<>();
        for (int request : requests) {
            requestList.add(request);
        }

        Collections.sort(requestList);

        int totalSeekOperations = 0;
        int currentPosition = head;
        StringBuilder sequence = new StringBuilder();
        sequence.append("Head Movement Sequence: ").append(currentPosition);

        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        for (int request : requestList) {
            if (request < head) {
                left.add(request);
            } else {
                right.add(request);
            }
        }

        if (leftDirection) {
            for (int request : left) {
                totalSeekOperations += Math.abs(request - currentPosition);
                currentPosition = request;
                sequence.append(" -> ").append(currentPosition);
            }

            if (!right.isEmpty()) {
                totalSeekOperations += Math.abs(right.get(right.size()-1) - currentPosition);
                currentPosition = right.get(right.size()-1);
                sequence.append(" -> ").append(currentPosition);

                for (int i = right.size()-2; i >= 0; i--) {
                    totalSeekOperations += Math.abs(right.get(i) - currentPosition);
                    currentPosition = right.get(i);
                    sequence.append(" -> ").append(currentPosition);
                }
            }
        } else {
            for (int request : right) {
                totalSeekOperations += Math.abs(request - currentPosition);
                currentPosition = request;
                sequence.append(" -> ").append(currentPosition);
            }

            if (!left.isEmpty()) {
                totalSeekOperations += Math.abs(left.get(0) - currentPosition);
                currentPosition = left.get(0);
                sequence.append(" -> ").append(currentPosition);

                for (int i = 1; i < left.size(); i++) {
                    totalSeekOperations += Math.abs(left.get(i) - currentPosition);
                    currentPosition = left.get(i);
                    sequence.append(" -> ").append(currentPosition);
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("C-LOOK Disk Scheduling Algorithm\n");
        result.append("Initial Head Position: ").append(head).append("\n");
        result.append("Direction: ").append(leftDirection ? "Left" : "Right").append("\n");
        result.append(sequence.toString()).append("\n");
        result.append("Total Seek Operations: ").append(totalSeekOperations).append("\n");
        result.append("Average Seek Length: ").append((double) totalSeekOperations / requests.length).append("\n");

        return result.toString();
    }

    private String calculateSCANDisk(int[] requests, int head, boolean leftDirection) {
        ArrayList<Integer> requestList = new ArrayList<>();
        for (int request : requests) {
            requestList.add(request);
        }

        Collections.sort(requestList);

        int totalSeekOperations = 0;
        int currentPosition = head;
        StringBuilder sequence = new StringBuilder();
        sequence.append("Head Movement Sequence: ").append(currentPosition);

        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        for (int request : requestList) {
            if (request < head) {
                left.add(request);
            } else {
                right.add(request);
            }
        }

        if (leftDirection) {
            // Add 0 (start of disk) if not already in requests
            if (!left.contains(0)) {
                left.add(0);
                Collections.sort(left);
            }

            Collections.reverse(left);
            for (int request : left) {
                totalSeekOperations += Math.abs(request - currentPosition);
                currentPosition = request;
                sequence.append(" -> ").append(currentPosition);
            }

            for (int request : right) {
                totalSeekOperations += Math.abs(request - currentPosition);
                currentPosition = request;
                sequence.append(" -> ").append(currentPosition);
            }
        } else {
            // Add 199 (end of disk) if not already in requests
            if (!right.contains(199)) {
                right.add(199);
                Collections.sort(right);
            }

            for (int request : right) {
                totalSeekOperations += Math.abs(request - currentPosition);
                currentPosition = request;
                sequence.append(" -> ").append(currentPosition);
            }

            Collections.reverse(left);
            for (int request : left) {
                totalSeekOperations += Math.abs(request - currentPosition);
                currentPosition = request;
                sequence.append(" -> ").append(currentPosition);
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("SCAN Disk Scheduling Algorithm\n");
        result.append("Initial Head Position: ").append(head).append("\n");
        result.append("Direction: ").append(leftDirection ? "Left" : "Right").append("\n");
        result.append(sequence.toString()).append("\n");
        result.append("Total Seek Operations: ").append(totalSeekOperations).append("\n");
        result.append("Average Seek Length: ").append((double) totalSeekOperations / requests.length).append("\n");

        return result.toString();
    }

    private String calculateCSCANDisk(int[] requests, int head, boolean leftDirection) {
        ArrayList<Integer> requestList = new ArrayList<>();
        for (int request : requests) {
            requestList.add(request);
        }

        Collections.sort(requestList);

        int totalSeekOperations = 0;
        int currentPosition = head;
        StringBuilder sequence = new StringBuilder();
        sequence.append("Head Movement Sequence: ").append(currentPosition);

        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        for (int request : requestList) {
            if (request < head) {
                left.add(request);
            } else {
                right.add(request);
            }
        }

        if (leftDirection) {
            // Add 0 (start of disk) if not already in requests
            if (!left.contains(0)) {
                left.add(0);
                Collections.sort(left);
            }

            for (int request : left) {
                totalSeekOperations += Math.abs(request - currentPosition);
                currentPosition = request;
                sequence.append(" -> ").append(currentPosition);
            }

            // Jump to end of disk
            if (!right.isEmpty()) {
                totalSeekOperations += Math.abs(199 - currentPosition);
                currentPosition = 199;
                sequence.append(" -> ").append(currentPosition);

                Collections.reverse(right);
                for (int request : right) {
                    totalSeekOperations += Math.abs(request - currentPosition);
                    currentPosition = request;
                    sequence.append(" -> ").append(currentPosition);
                }
            }
        } else {
            // Add 199 (end of disk) if not already in requests
            if (!right.contains(199)) {
                right.add(199);
                Collections.sort(right);
            }

            for (int request : right) {
                totalSeekOperations += Math.abs(request - currentPosition);
                currentPosition = request;
                sequence.append(" -> ").append(currentPosition);
            }

            // Jump to start of disk
            if (!left.isEmpty()) {
                totalSeekOperations += Math.abs(0 - currentPosition);
                currentPosition = 0;
                sequence.append(" -> ").append(currentPosition);

                for (int request : left) {
                    totalSeekOperations += Math.abs(request - currentPosition);
                    currentPosition = request;
                    sequence.append(" -> ").append(currentPosition);
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("C-SCAN Disk Scheduling Algorithm\n");
        result.append("Initial Head Position: ").append(head).append("\n");
        result.append("Direction: ").append(leftDirection ? "Left" : "Right").append("\n");
        result.append(sequence.toString()).append("\n");
        result.append("Total Seek Operations: ").append(totalSeekOperations).append("\n");
        result.append("Average Seek Length: ").append((double) totalSeekOperations / requests.length).append("\n");

        return result.toString();
    }

    public static void main(String[] args) {
        AlgorithmOSProj frame = new AlgorithmOSProj();
        frame.setTitle("Operating System Algorithms");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}