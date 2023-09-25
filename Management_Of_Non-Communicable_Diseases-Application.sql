CREATE TABLE `facilities` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `address` text,
  `phone_number` varchar(255),
  `email` varchar(255),
  `deleted_at` datetime
);

CREATE TABLE `departments` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `description` text,
  `deleted_at` datetime
);

CREATE TABLE `department_facilities` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `facility_id` int,
  `department_id` int
);

CREATE TABLE `accounts` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `department_facility_id` int,
  `user_name` varchar(255),
  `password` varchar(255) COMMENT 'bcrypt',
  `level` int,
  `deleted_at` datetime
);

CREATE TABLE `specializations` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255)
);

CREATE TABLE `job_codes` (
  `id` varchar(255) PRIMARY KEY,
  `name` varchar(255),
  `job_level` int,
  `grade` int,
  `coefficients_salary` float
);

CREATE TABLE `positions` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `bonus` float
);

CREATE TABLE `staffs` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `department_facilities_id` int,
  `job_code` varchar(255),
  `position` int,
  `specialization_id` int,
  `first_name` varchar(255),
  `last_name` varchar(255),
  `phone_number` varchar(255),
  `email` varchar(255),
  `id_number` varchar(255),
  `base_salary` float,
  `start_work` date,
  `deleted_at` datetime
);

CREATE TABLE `diseases` (
  `id` varchar(255) PRIMARY KEY,
  `name` varchar(255)
);

CREATE TABLE `medicine_groups` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `description` varchar(255)
);

CREATE TABLE `medicine_types` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `diseases` varchar(255)
);

CREATE TABLE `medicines` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `group_id` int,
  `type_id` int,
  `name` varchar(255),
  `unit` varchar(255),
  `description` text,
  `instruction` text,
  `deleted_at` datetime
);

CREATE TABLE `medicine_import_history` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `facility_id` int,
  `total_quantity` int,
  `import_date` datetime,
  `deleted_at` datetime
);

CREATE TABLE `medicine_import_history_details` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `import_history_id` int,
  `medicine_id` int,
  `manufacturer` varchar(255),
  `manufacture_date` date,
  `expiration_date` date,
  `quantity` int,
  `purchase_price` float,
  `note` text,
  `deleted_at` datetime
);

CREATE TABLE `medicine_export_history` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `facility_id` int,
  `total_quantity` int,
  `export_date` datetime,
  `deleted_at` datetime
);

CREATE TABLE `medicine_export_history_details` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `export_history_id` int,
  `medicine_id` int,
  `manufacturer` varchar(255),
  `manufacture_date` date,
  `expiration_date` date,
  `quantity` int,
  `selling_price` float,
  `note` text,
  `deleted_at` datetime
);

CREATE TABLE `medicine_inventories` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `medicine_id` int,
  `facility_id` int,
  `total_quantity` int,
  `last_import_date` datetime,
  `last_export_date` datetime
);

CREATE TABLE `equpiment_types` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255)
);

CREATE TABLE `equpiments` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `type` int,
  `description` text,
  `instruction` text,
  `deleted_at` datetime
);

CREATE TABLE `equpiment_import_history` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `facility_id` int,
  `total_quantity` int,
  `import_date` datetime,
  `deleted_at` datetime
);

CREATE TABLE `equpiment_import_history_details` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `import_history_id` int,
  `equpiment_id` int,
  `manufacturer` varchar(255),
  `manufacture_date` date,
  `expiration_date` date,
  `quantity` int,
  `purchase_price` float,
  `note` text,
  `deleted_at` datetime
);

CREATE TABLE `equpiment_export_history` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `facility_id` int,
  `total_quantity` int,
  `export_date` datetime,
  `deleted_at` datetime
);

CREATE TABLE `equpiment_export_history_details` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `export_history_id` int,
  `equpiment_id` int,
  `manufacturer` varchar(255),
  `manufacture_date` date,
  `expiration_date` date,
  `quantity` int,
  `purchase_price` float,
  `note` text,
  `deleted_at` datetime
);

CREATE TABLE `equpiment_inventories` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `equpiment_id` int,
  `facility_id` int,
  `total_quantity` int,
  `last_import_date` datetime,
  `last_export_date` datetime
);

CREATE TABLE `People` (
  `people_id` int PRIMARY KEY,
  `insurance` varchar(255),
  `name` varchar(255),
  `date_of_birth` date,
  `gender` varchar(255),
  `address` varchar(255),
  `phone_number` varchar(255),
  `email` varchar(255),
  `note` text,
  `deleted_at` datetime
);

CREATE TABLE `Insurance` (
  `insurance_id` varchar(255) PRIMARY KEY,
  `start_date` date,
  `expiration_date` date,
  `deleted_at` datetime
);

CREATE TABLE `diagnosis` (
  `diagnosis_id` int PRIMARY KEY,
  `people_id` int,
  `facility_id` int,
  `staff_id` int,
  `systolic_blood_pressure` int,
  `diastolic_blood_pressure` int,
  `blood_glucose_index` int,
  `symptoms` varchar(255),
  `complications` varchar(255),
  `diagnosis_result` varchar(255),
  `diagnosis_date` date,
  `deleted_at` datetime
);

CREATE TABLE `treatment` (
  `treatment_id` int PRIMARY KEY,
  `diagnosis_id` int,
  `treatment_date` date,
  `Treat_note` text,
  `deleted_at` datetime
);

CREATE TABLE `Patients` (
  `patient_id` int PRIMARY KEY,
  `first_name` varchar(255),
  `last_name` varchar(255),
  `date_of_birth` date,
  `gender` ENUM ('Female', 'Male', 'Other'),
  `address` text,
  `phone_number` varchar(255),
  `email` varchar(255)
);

CREATE TABLE `MedicalHistory` (
  `medical_history_id` int PRIMARY KEY,
  `patient_id` int,
  `doctor_id` int,
  `diagnosis` text,
  `treatment` text,
  `admission_date` datetime,
  `discharge_date` datetime
);

ALTER TABLE `department_facilities` ADD FOREIGN KEY (`facility_id`) REFERENCES `facilities` (`id`);

ALTER TABLE `department_facilities` ADD FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`);

ALTER TABLE `accounts` ADD FOREIGN KEY (`department_facility_id`) REFERENCES `department_facilities` (`id`);

ALTER TABLE `staffs` ADD FOREIGN KEY (`department_facilities_id`) REFERENCES `department_facilities` (`id`);

ALTER TABLE `staffs` ADD FOREIGN KEY (`job_code`) REFERENCES `job_codes` (`id`);

ALTER TABLE `staffs` ADD FOREIGN KEY (`position`) REFERENCES `positions` (`id`);

ALTER TABLE `staffs` ADD FOREIGN KEY (`specialization_id`) REFERENCES `specializations` (`id`);

ALTER TABLE `medicine_types` ADD FOREIGN KEY (`diseases`) REFERENCES `diseases` (`id`);

ALTER TABLE `medicines` ADD FOREIGN KEY (`group_id`) REFERENCES `medicine_groups` (`id`);

ALTER TABLE `medicines` ADD FOREIGN KEY (`type_id`) REFERENCES `medicine_types` (`id`);

ALTER TABLE `medicine_import_history` ADD FOREIGN KEY (`facility_id`) REFERENCES `facilities` (`id`);
