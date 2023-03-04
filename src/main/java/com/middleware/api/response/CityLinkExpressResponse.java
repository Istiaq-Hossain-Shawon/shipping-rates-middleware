package com.middleware.api.response;


public class CityLinkExpressResponse {
    private Req req;

    public Req getReq() {
        return req;
    }

    public void setReq(Req req) {
        this.req = req;
    }

    public static class Req {
        private Data data;
        
        private int status;

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public static class Data {
            private Double rate;
            private String code;
            private int api_days;
            private int final_days;
            private String dayString;
            private int weekendDays;
            private String message;
			public Double getRate() {
				return rate;
			}
			public void setRate(Double rate) {
				this.rate = rate;
			}
			public String getCode() {
				return code;
			}
			public void setCode(String code) {
				this.code = code;
			}
			public int getApi_days() {
				return api_days;
			}
			public void setApi_days(int api_days) {
				this.api_days = api_days;
			}
			public int getFinal_days() {
				return final_days;
			}
			public void setFinal_days(int final_days) {
				this.final_days = final_days;
			}
			public String getDayString() {
				return dayString;
			}
			public void setDayString(String dayString) {
				this.dayString = dayString;
			}
			public int getWeekendDays() {
				return weekendDays;
			}
			public void setWeekendDays(int weekendDays) {
				this.weekendDays = weekendDays;
			}
			public String getMessage() {
				return message;
			}
			public void setMessage(String message) {
				this.message = message;
			}
            

            
        }
    }
}
