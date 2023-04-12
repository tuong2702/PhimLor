USE [master]
GO
/****** Object:  Database [AsmJava4]    Script Date: 4/12/2023 9:26:30 PM ******/
CREATE DATABASE [AsmJava4]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'AsmJava4', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\AsmJava4.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'AsmJava4_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\AsmJava4_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [AsmJava4] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [AsmJava4].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [AsmJava4] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [AsmJava4] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [AsmJava4] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [AsmJava4] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [AsmJava4] SET ARITHABORT OFF 
GO
ALTER DATABASE [AsmJava4] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [AsmJava4] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [AsmJava4] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [AsmJava4] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [AsmJava4] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [AsmJava4] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [AsmJava4] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [AsmJava4] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [AsmJava4] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [AsmJava4] SET  DISABLE_BROKER 
GO
ALTER DATABASE [AsmJava4] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [AsmJava4] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [AsmJava4] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [AsmJava4] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [AsmJava4] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [AsmJava4] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [AsmJava4] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [AsmJava4] SET RECOVERY FULL 
GO
ALTER DATABASE [AsmJava4] SET  MULTI_USER 
GO
ALTER DATABASE [AsmJava4] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [AsmJava4] SET DB_CHAINING OFF 
GO
ALTER DATABASE [AsmJava4] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [AsmJava4] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [AsmJava4] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [AsmJava4] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'AsmJava4', N'ON'
GO
ALTER DATABASE [AsmJava4] SET QUERY_STORE = ON
GO
ALTER DATABASE [AsmJava4] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [AsmJava4]
GO
/****** Object:  Table [dbo].[Favorites]    Script Date: 4/12/2023 9:26:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Favorites](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userId] [varchar](50) NOT NULL,
	[videoId] [varchar](50) NOT NULL,
	[likeDate] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Shares]    Script Date: 4/12/2023 9:26:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Shares](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [varchar](50) NOT NULL,
	[VideoId] [varchar](50) NOT NULL,
	[emails] [varchar](max) NOT NULL,
	[shareDate] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 4/12/2023 9:26:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[id] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[email] [varchar](max) NOT NULL,
	[fullname] [nvarchar](max) NOT NULL,
	[admin] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Videos]    Script Date: 4/12/2023 9:26:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Videos](
	[id] [varchar](50) NOT NULL,
	[title] [nvarchar](max) NOT NULL,
	[poster] [varchar](max) NULL,
	[views] [int] NOT NULL,
	[description] [nvarchar](max) NOT NULL,
	[active] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[Users] ADD  DEFAULT ((1)) FOR [admin]
GO
ALTER TABLE [dbo].[Videos] ADD  DEFAULT ((0)) FOR [active]
GO
ALTER TABLE [dbo].[Favorites]  WITH CHECK ADD FOREIGN KEY([userId])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Favorites]  WITH CHECK ADD FOREIGN KEY([videoId])
REFERENCES [dbo].[Videos] ([id])
GO
ALTER TABLE [dbo].[Shares]  WITH CHECK ADD FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Shares]  WITH CHECK ADD FOREIGN KEY([VideoId])
REFERENCES [dbo].[Videos] ([id])
GO
USE [master]
GO
ALTER DATABASE [AsmJava4] SET  READ_WRITE 
GO
