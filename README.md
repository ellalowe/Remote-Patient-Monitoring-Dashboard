# Remote Patient Monitoring Dashboard



## What will the application do?

The Remote Patient Monitoring Dashboard application will enable healthcare providers or caregivers to remotely track and monitor the health status of patients. It helps in managing patients with chronic conditions or those who need continuous supervision by collecting and displaying health data, generating alerts, and providing insights into the patient's condition.


## Who will use it?

The Remote Patient Monitoring Dashboard could potentially be used by healthcare providers, caregivers, and patients. Healthcare providers could use it to monitor patient health remotely and receive alerts for critical conditions. Caregivers, including family members, use it to keep track of a loved one’s health status and ensure timely care. Patients themselves use it to monitor their health metrics and communicate more effectively with their healthcare team.

## Why is this project of interest to you?
This project interests me because it allows me to apply my passion for healthcare in a meaningful way. By developing tools that enable remote monitoring, I aim to make healthcare more accessible and efficient, particularly for those who need continuous supervision. This project aligns with my goal of leveraging technology to improve patient care and outcomes.


## User Stories:
- As a healthcare provider, I want to add multiple patient records to the dashboard so that I can view their information.
- As a healthcare provider, I want to list all patient records in the dashboard so that I can quickly see all monitored patients.
- As a healthcare provider, I want to remove a patient record from the dashboard so that I can update the list when monitoring ends.
- As a healthcare provider, I want to mark a patient as "Critical" on the dashboard so that I can prioritize their care.
- As a healthcare provider, I want to have the option to load previously saved patient health data from a file when I start the application so that I can continue monitoring from where I left off.
- As a healthcare provider, I want to be able to save my patients to the dashboard.

## Instructions for End User

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by selecting a patient and then pressing the 'Mark As Critical' button
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by selecting either filter by 'Normal' or 'Critical' 
in the drop down list. Then selecting the button 'Filter' for the action to execute. 
- The visual component is a splash screen that appears for 3 seconds when the application is run, the image itself is located in the data folder.
- You can save the state of my application by selecting the 'Save' button.
- You can reload the state of my application by selecting the 'Load' button.

## Phase 4: Task 2
- Thu Nov 28 16:04:38 PST 2024: Patient added: Ella
- Thu Nov 28 16:04:43 PST 2024: Patient added: Daniel
- Thu Nov 28 16:04:45 PST 2024: Marked patient as critical: Daniel
- Thu Nov 28 16:04:49 PST 2024: Filtered patients by status: Normal
- Thu Nov 28 16:04:51 PST 2024: Filtered patients by status: Critical

## Phase 4: Task 3
An improvement that could be made could address the tight coupling between PatientDashboardGUI and Dashboard. By introducing an interface for Dashboard, the GUI could depend on an abstraction rather than the concrete Dashboard class. This would make the GUI more flexible and allow for alternative implementations of Dashboard in the future. It helps improve scalability making the system better equipped to handle future enhancements.



